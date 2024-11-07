provider "aws" {
  region = "us-west-2"
}

# module
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

module "s3" {
  source = "../common_modules/s3"
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
