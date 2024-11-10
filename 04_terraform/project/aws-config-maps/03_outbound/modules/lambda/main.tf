resource "aws_lambda_function" "example_lambda_1" {
  function_name = "example_lambda_1"
  role          = var.lambda_role_arn
  handler       = "index.handler"
  runtime       = "python3.8"
  filename      = "path/to/your/lambda_1.zip"
}

resource "aws_lambda_function" "example_lambda_2" {
  function_name = "example_lambda_2"
  role          = var.lambda_role_arn
  handler       = "index.handler"
  runtime       = "python3.8"
  filename      = "path/to/your/lambda_2.zip"
}
