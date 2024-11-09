locals{
  bucket_prefix = "${var.app_name}-${var.app_component}-"
  bucket_access_logging = "${var.app_name}-${var.app_component}-s3-access_logs"
}

module "outbound_archive_bucket" {
  source = "../modules/s3"
  bucket_name           = "${local.bucket_prefix}-${var.aws_primary_region}-archive-bucket"
  replicate_bucket_name = "${local.bucket_prefix}-${var.aws_secondary_region}-archive-bucket"
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
  attach_policy_flag = false
}

