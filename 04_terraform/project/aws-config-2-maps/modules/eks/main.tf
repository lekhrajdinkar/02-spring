module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  cluster_name    = "example-eks-cluster"
  cluster_version = "1.24"
  subnets         = var.subnet_ids
  vpc_id          = var.vpc_id
}
