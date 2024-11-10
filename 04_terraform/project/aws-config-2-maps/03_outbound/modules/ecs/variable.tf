variable "app_name" { type = string }
variable "app_component" { type = string }
variable "app_env" { type = string }

variable "aws_primary_region" { type = string }
variable "aws_account_id" { type = string }
variable "tags" { type = map(string) }

variable "vpc_id" { type = string }

variable "desired_count" { type = number }

variable "cpu" { type = string }
variable "memory" { type = string }
variable "container_image" { type = string }
variable "container_port" { type = string }
variable "host_port" { type = string }

variable "container_env_vars" { type = list(any) }
variable "secrets" { type = list(any) }
