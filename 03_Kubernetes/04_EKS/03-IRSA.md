# IRSA
- AWS-IAM-role for k8s-service-Accounts.
- allows assigning AWS IAM roles to Kubernetes Service Accounts on Amazon EKS clusters.
- enables pods running in the cluster to securely access AWS resources.
- benefits:
  - IRSA leverages AWS’s `temporary` credentials.
  - `Fine-grained access`-  Each pod can assume an IAM role with specific permissions.
  - `Secure authentication` - Uses Amazon’s OpenID Connect (OIDC) provider to securely authenticate Service Accounts with IAM.
    - EKS-cluster(oidc-1) > NS > create SA
    - oidc-1 will authenticate SA :point_left:


## Steps
- https://chatgpt.com/c/67240e7e-6e38-800d-8e1d-3b7f1a8fe509
- Enable the OIDC Provider for Your EKS Cluster
  - https://docs.aws.amazon.com/eks/latest/userguide/enable-iam-roles-for-service-accounts.html
  - check: **aws eks describe-cluster --name <your-cluster-name> --query "cluster.identity.oidc.issuer" --output text**
- Create an IAM role - `role-1-sa-1`
  - **permission policy**
  - **trust policy**
    - trusted principle - OIDC provider federated user.
    - serviceAccount in k8s object > authenticated by OIDC > become federated user to assume role.  :point_left:
    - notice condition: audience is `sa of ns`
    - check this : [trusted_policy_k8s_federated_sa.tftpl](..%2F..%2F04_terraform%2Fproject%2Faws-config-maps%2F03_outbound%2Fmodules%2Feks%2Ftrusted_policy_k8s_federated_sa.tftpl)
- create sa-1
  - add annotation : eks.amazonaws.com/role-arn: arn:aws:iam::<account-id>:role/`role-1-sa-1`
- Associate the Service Account with Your Pods. 
  - serviceAccountName: sa-1

## summary
- The Kubernetes service account (sa-1) is used by a pod running in the EKS cluster.
- `sa-1` is annotated with the ARN of `iam-role-1`, allowing it to be linked to AWS permissions.
- `oidc-1`, acts as a federated identity provider for `sa-1`
- sa-1 gets token from odic-1
- token verified by iam:identity provider
- trusted policy of `iam-role-1`, permits sa-1 to assume it.