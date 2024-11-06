# IAM role for service Accounts
- allows assigning AWS IAM roles to Kubernetes Service Accounts on Amazon EKS clusters.
- enables pods running in the cluster to securely access AWS resources.
- benefits:
  - IRSA leverages AWS’s `temporary` credentials.
  - `Fine-grained access`-  Each pod can assume an IAM role with specific permissions.
  - `Secure authentication` - Uses Amazon’s OpenID Connect (OIDC) provider to securely authenticate Service Accounts with IAM.
- AWS SDK after 2019, supports it.

## Steps
- https://chatgpt.com/c/67240e7e-6e38-800d-8e1d-3b7f1a8fe509
- Enable the OIDC Provider for Your EKS Cluster
  - https://docs.aws.amazon.com/eks/latest/userguide/enable-iam-roles-for-service-accounts.html
  - check: **aws eks describe-cluster --name <your-cluster-name> --query "cluster.identity.oidc.issuer" --output text**
- Create an IAM role - `role-1-sa-1`
  - permission policy
  - trust policy
    - trusted principle - OIDC provider federated user.
    - serviceAccount in k8s object > authenticated by OIDC > become federated user to assume role.  <<<
- create sa-1
  - add anno : eks.amazonaws.com/role-arn: arn:aws:iam::<account-id>:role/`role-1-sa-1`
- Associate the Service Account with Your Pods. 
  - serviceAccountName: sa-1

## create OIDC Provider for Your EKS Cluster
- install `eksctl` - https://eksctl.io/installation/
- **eksctl utils associate-iam-oidc-provider --cluster $cluster_name --approve**
- can also create from console.