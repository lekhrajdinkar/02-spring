resource "aws_sqs_queue" "standard_queue" {
  name = "standard-queue-example"
}

resource "aws_sqs_queue" "fifo_queue" {
  name       = "fifo-queue-example.fifo"
  fifo_queue = true
}
