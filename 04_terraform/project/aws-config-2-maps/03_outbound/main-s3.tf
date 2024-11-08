locals{
  bucket_prefix = "${var.app_name}-${var.app_component}-"
  bucket_access_logging = "${var.app_name}-${var.app_component}-s3-access_logs"
}
module "outbound_archive_bucket" {
  source = "../modules/s3"
  bucket_name           = "${local.bucket_prefix}-s3-${var.aws_primary_region}-archiveBucket"
  replicate_bucket_name = "${local.bucket_prefix}-s3-${var.aws_secondary_region}-archiveBucket"
  force_destroy_flag    = false
  primary_region        = var.aws_primary_region
  secondary_region      = var.aws_secondary_region
  replicate_flag         = true
  logging_configs       = {
    target_bucket = local.bucket_access_logging
    target_prefix = "outbound_archive_bucket"
  }
  mft_account_arn       = ""
  sse_configs           = {}
  tags                  = var.tags
  versioning_configs    = {
    enabled = false
    mfa_delete_enabled = false
  }
  aws_account_alias     = var.aws_account_alias
}

/*
module "ecr" {
  source = "../common_modules/ecr"
}

module "vpc" {
  source = "../common_modules/vpc"
}

module "eks" {
  source = "../common_modules/eks"
  vpc_id = module.vpc.vpc_id
  subnet_ids = module.vpc.public_subnets
}

module "sns" {
  source = "../common_modules/sns"
}

module "lambda" {
  source = "../common_modules/lambda"
}

module "eventbridge" {
  source = "../common_modules/eventbridge"
}

module "sqs" {
  source = "../common_modules/sqs"
}
*/
