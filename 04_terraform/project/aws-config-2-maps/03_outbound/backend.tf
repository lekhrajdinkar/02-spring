provider "aws" {
  region = var.aws_primary_region
  assume_role {
    role_arn = var.aws_assume_role_arn
  }
}

provider "aws" {
  region = var.aws_secondary_region
  alias = "secondary"
  assume_role {
    role_arn = var.aws_assume_role_arn
  }
}

terraform {
  cloud {

    organization = "lekhrajdinkar-org"

    workspaces {
      name = "aws-config-2-maps-outbound"
    }
  }
}

# aF5uyVtZUU6E4w.atlasv1.yOV2m3IEqwzIa6qcEtoBI
# UeyrlQqj4ELa2WZscKFvZMGmjdOi9wHyW5zIvc5ovZZvwg