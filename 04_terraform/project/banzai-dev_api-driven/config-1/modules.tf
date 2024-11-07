# import child modules

module "module_1" {
  source = "./module-1"
}

module "module_2" {
  source = "./module-2"
  #source  = "terraform-aws-modules/vpc/aws"
}