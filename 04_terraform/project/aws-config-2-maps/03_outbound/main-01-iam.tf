locals {
  inline_policy_template_1 = "policy_07_s3-primary-read"
  inline_policy_template_2 = "policy_07_s3-primary-replication"
  trusted_policy_template = "trusted_policy"
}

module "s3_archive_bucket_role" {
  source = "./modules/iam"
  permission_boundary_name = ""
  policy_desc = ""
  policy_name = ""
  policy_templatefile_name = local.inline_policy_template_1

  policy_templatefile_value_map = {
    region            = var.aws_primary_region
    aws_account_id    = var.aws_account_id
    app_name          = var.app_name
    app_component     = var.app_component
    app_env           = var.app_env
    bucket_name       = "archive_bucket"
  }

  role_name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-s3-role-1"
  tags = var.tags
  trusted_service = "s3"
}