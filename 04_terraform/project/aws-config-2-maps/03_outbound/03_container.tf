/*
data "aws_secretsmanager_secret" "db_password"{
  arn = ""
}
*/

locals {
  container_env_vars  = [
    {
      name = "SPRING_PROFILE_ACTIVE"
      value = var.app_env
    },
    {
      name  = "DB_PASSWORD"
      value = ""
    }
  ]

  secrets             = [
    {
      name      = "DB_PASSWORD"
      valueFrom = ""
    }
  ]
}

module "ecr"{
  source = "./modules/ecr"

  app_component = var.app_component
  app_env = var.app_env
  app_name = var.app_name
  aws_primary_region = var.aws_primary_region
  tags = var.tags
}

module "ecs"{
  source = "./modules/ecs"

  app_component       = var.app_component
  app_env             = var.app_env
  app_name            = var.app_name
  aws_account_id      = var.aws_account_id
  aws_primary_region  = var.aws_primary_region
  tags                = var.tags
  vpc_id              = var.aws_vpc_id

  container_image = "${module.ecr.ecr_repository_url}:latest"
  container_port  = 8080
  host_port       = 8080
  memory          = 1024
  cpu             = 512

  desired_count     = 0

  container_env_vars = local.container_env_vars
  secrets            = local.secrets
  policy_templatefile_name = "policy_03_ecs_task_exec"

  depends_on = [module.ecr]

}