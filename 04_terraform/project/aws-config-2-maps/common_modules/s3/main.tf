resource "aws_s3_bucket" "primary" {
  bucket = "primary-bucket-example"
}

resource "aws_s3_bucket" "dr" {
  bucket = "dr-bucket-example"
}
