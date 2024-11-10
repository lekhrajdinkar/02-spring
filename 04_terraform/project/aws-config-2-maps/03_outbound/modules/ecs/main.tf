data "aws_subnets" "subnets" {
  filter {
    name   = "vpc-id"
    values = [var.vpc_id] # Replace with your VPC ID
  }
}

# 1. ECS cluster
resource "aws_ecs_cluster" "cluster" {
  name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-cluster"
  tags = var.tags
}

# 2.IAM role for ECS tasks
module "ecs_task_role"{
  source = "../iam"
  policy_desc = "ecs_task_role"
  policy_name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-task-policy"

  policy_templatefile_name = var.policy_templatefile_name
  policy_templatefile_value_map = {
    region            = var.aws_primary_region
    aws_account_id    = var.aws_account_id
    app_name          = var.app_name
    app_component     = var.app_component
    app_env           = var.app_env
  }

  role_name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-task-role"
  tags = var.tags
  trusted_service = "ecs-tasks"
}

# 3. log group for ecs task
resource "aws_cloudwatch_log_group" "log_group" {
  name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-log-group"
  retention_in_days = 7
  tags = var.tags
}

# 4. ECS task-definition >> container definition
resource "aws_ecs_task_definition" "task" {
  family                = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-task-definition"
  network_mode          = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                   = var.cpu
  memory                = var.memory

  execution_role_arn    = module.ecs_task_role.arn

  container_definitions = templatefile("${path.module}/container_template.tftpl", {
    container_name    = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-container"
    container_image   = var.container_image
    container_port    = var.container_port
    host_port         = var.host_port
    env_vars          = jsonencode(var.container_env_vars)
    secrets           = jsonencode(var.secrets)
    log_group_name    = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-log-group"
    log_stream_prefix = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}"
    region            = var.aws_primary_region
    cpu = var.cpu
    memory = var.memory
  })

  depends_on = [module.ecs_task_role, aws_cloudwatch_log_group.log_group]
}

# ecs service sg
resource "aws_security_group" "ecs_service_sg" {
  name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-service-sg"
}

# ECS service
resource "aws_ecs_service" "service" {
  name            = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-ecs-service"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.task.arn
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = data.aws_subnets.subnets.ids
    security_groups  = [ aws_security_group.ecs_service_sg.id ]
    assign_public_ip = false
  }

  desired_count = var.desired_count
}
