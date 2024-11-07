data "aws_caller_identity" "current" {}
data "aws_iam_policy" "permission-boundary"{
  arn = "arn:aws:iam::${data.aws_caller_identity.current.account_id}:policy/ec2.amazonaws.com"
}

data "aws_iam_policy_document" "organization-policy" {
  statement {
    effect = "Allow"
    principal = {
      type       = "AWS"
      identifier = "arn:aws:iam::${data.aws_caller_identity.current.id}:root"
    }
    action    = ["s3:*"]
    resources = [aws_s3_bucket.this[0].arn, "${aws_s3_bucket.this[0].arn}/*"]
  }
}

data "aws_iam_policy_document" "this" {
  count = var.create_bucket_flag ? 1 : 0
  source_policy_documents = [
    data.aws_iam_policy_document.organization-policy,
    var.policy
  ]
}

locals {
  kms_region = lookup({"us-east-1"="useast1", "us-west-2"="uswest2"}, var.primary_region)
  replication_kms_region = lookup({"us-east-1"="useast1", "us-west-2"="uswest2"}, var.secondary_region)
}
data "aws_kms_key" "s3_key" {
  key_id = "alias/${var.aws_account_alias}/${local.kms_region}/s3/0/kek"
}
data "aws_kms_key" "replication_s3_key" {
  key_id = "alias/${var.aws_account_alias}/${local.replication_kms_region}/s3/0/kek"
}