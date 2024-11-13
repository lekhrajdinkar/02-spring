## EKS OIDC provider
- found 2 use cases.
- When the EKS cluster is created, an OIDC provider (oidc-1) is automatically associated with it or manually configured.
- allows authentication tokens to be issued for both : `user` and `SA`.
### 1. user - mcp-user-1
- represent `IAM user` or `IAM role` 
- admin applied policy to interact with EKS to this - eks:AccessKubernetesApi, etc
- thus, user can make `aws eks get-token` --> interacts with oidc-1 --> get token-1
  - this token is presented to eks cluster apiserver.
- RBAC for this user:
  - admin created `Role` and Role-Binding for this user (ns-level resource) : 
    - admin added none.
    - developer with handle  ns-level.
  - admin created `ClusterRole` and Cluster-Role-Binding for this user (cluster-level resource. eg: ns) 
    - access ns - `maps-dev-ns`, `maps-qa-ns` and `maps-prod-ns`, using **aws-auth** config map.
- :question: for cg:
  - seems mcp-user-1 is common to all teams ?
    
### 2. service account - sa-1 (IRSA)
- The Kubernetes service account (sa-1) is used by a pod running in the EKS cluster.
- `sa-1` is annotated with the ARN of `iam-role-1`, allowing it to be linked to AWS permissions.
- `oidc-1`, acts as a federated identity provider for `sa-1`
- sa-1 gets token from odic-1
- token verified by iam
- trusted policy of `iam-role-1`, permits sa-1 to assume it.
