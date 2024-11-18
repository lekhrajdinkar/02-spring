# create fargate profile
```
apiVersion: eks.amazonaws.com/v1
kind: FargateProfile
metadata:
  name: dev-fargate-profile
spec:
  clusterName: your-eks-cluster-name
  podExecutionRoleArn: arn:aws:iam::123456789012:role/your-pod-execution-role
  selectors:
    - namespace: dev-ns
```


```
aws eks create-fargate-profile \
  --cluster-name your-eks-cluster-name \
  --fargate-profile-name dev-fargate-profile \
  --namespace dev-ns \
  --pod-execution-role-arn arn:aws:iam::123456789012:role/your-pod-execution-role

```

```
resource "aws_eks_fargate_profile" "eks_fargate_profile" {
  cluster_name = aws_eks_cluster.eks_cluster.name
  fargate_profile_name = "${local.prefix}-fargate-profile"
  pod_execution_role_arn = aws_iam_role.eks_pod_exec_role.arn

  subnet_ids = aws_subnet.eks_private_subnet[*].id

  selector {
    namespace = var.namespace
  }
  depends_on = [
    aws_eks_cluster.eks_cluster,
    aws_iam_role.eks_pod_exec_role
  ]
}
```