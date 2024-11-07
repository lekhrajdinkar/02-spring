# --- AWS ---
variable "aws_account_id" {
  type = string
  default = "533267082359"
}
variable "aws_account_alias" {
  type = string
  default = "lekhrajdinkar"
}
variable "aws_env" {
  type = string
  default = "dev"
}

variable "aws_assume_role_arn" {
  type = string
}

variable "aws_primary_region" {
  type = string
  default = "us-west-2"
}

variable "aws_secondary_region" {
  type = string
  default = "us-east-1"
}

variable "aws_vpc_id" {
  type = string
  default = "vpc-04ce2894d2f99bbb8"
}

variable "aws_zone_id" {
  type = string
  default = null
}

variable "tags" {
  type = map(string)
  default = {
    purpose = "learning"
    cost_center = "999999"
    app_name = "maps-outbound"
  }
}

# --- app ---
variable "app_name" {
  type = string
  default = "maps"
}
variable "app_component" {
  type = string
  default = "outbound"
}

variable "app_domain_name" {
  type = string
  default = null
}

# --- okta ---
variable "okta_domain_name" {
  type = string
  default = null
}

