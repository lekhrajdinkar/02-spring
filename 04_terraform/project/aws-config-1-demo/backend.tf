terraform {
  backend "remote" {
    organization = "lekhrajdinkar-org"
    workspaces {
      name = "aws-config-1"
      # hostname =
    }
  }
}