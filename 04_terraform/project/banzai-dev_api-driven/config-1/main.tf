terraform {
  cloud {
    organization = "lekhrajdinkar-org"
    workspaces {
      name = "banzai-dev_api-driven"
    }
  }

  required_providers {
    aws = {
      source  = "registry.terraform.io/hashicorp/aws"
      version = "~> 5.56.0"
    }
  }

  required_version = ">= 1.9.0"
}

provider "aws" {
  region  = "us-west-2"
  profile = "default" # AWS config
}

resource "aws_instance" "app_server" {
  ami           = "ami-830c94e3"
  instance_type = "t2.micro"

  tags = {
    Name = var.ec2_server_name
  }
}

variable "ec2_server_name" {
  type        = string
  default     = "appServerTerraform"
  description = "ec2 ami-0124ee9682f33ad99 t2.micro | freeTier"
}

variable "aws_access_key" {
  type        = string
  default     = ""
  description = "aws_access_key"
}

variable "aws_access_value" {
  type        = string
  default     = ""
  description = "aws_access_value"
}

