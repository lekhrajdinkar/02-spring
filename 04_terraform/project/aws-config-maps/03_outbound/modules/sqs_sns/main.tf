#############################################
# SNS
#############################################
# Create an SNS Topic
resource "aws_sns_topic" "topic_alert" {
  name = "${var.app_name}-${var.app_component}-${var.aws_primary_region}-${var.app_env}-alert"
  tags = var.tags
  # kms_master_key_id = ""
}

# Create an SNS Topic Policy
resource "aws_sns_topic_policy" "topic_policy" {
  arn = aws_sns_topic.topic_alert.arn
  # policy = jsonencode(local.sns_policy)
  policy = data.aws_iam_policy_document.sns_policy.json
}

# Create an SNS Topic Subscription (optional)
resource "aws_sns_topic_subscription" "topic_subscription" {
  topic_arn = aws_sns_topic.topic_alert.arn
  protocol  = "email"  # Change to "sms", "lambda", "http", etc., as needed
  endpoint  = "lekhrajdinkarus@gmail.com"  # Replace with the actual email or endpoint
}

#############################################
# SQS
#############################################
