provider "aws" {
  region = "us-west-2"
}

# module
module "ecr" {
  source = "./modules/ecr"
}

module "vpc" {
  source = "./modules/vpc"
}

module "eks" {
  source = "./modules/eks"
  vpc_id = module.vpc.vpc_id
  subnet_ids = module.vpc.public_subnets
}

module "s3" {
  source = "./modules/s3"
}

module "sns" {
  source = "./modules/sns"
}

module "lambda" {
  source = "./modules/lambda"
}

module "eventbridge" {
  source = "./modules/eventbridge"
}

module "sqs" {
  source = "./modules/sqs"
}
