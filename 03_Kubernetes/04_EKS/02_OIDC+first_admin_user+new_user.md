# A. on cluster creation
- When the EKS cluster is created, then it creates below 2 things as well:

## 1 `identity provider / OIDC`
- an OIDC provider (oidc-1) is automatically associated with it.
- if not found manually configured it:
  - install `eksctl` - https://eksctl.io/installation/
  - **eksctl utils associate-iam-oidc-provider --cluster $cluster_name --approve**
- **aws eks describe** --cluster-name xxxx : check issuer url.
- **oidc authentication logic** for:  :point_left:
  - user : must have entry in aws_auth configMAP.
  - sa   : always get authenticated,  since created inside cluster only.
  
## 2 `first admin user`
  - **the IAM entity (user or role) that creates the cluster** is automatically granted `system:masters` permissions in the `aws-auth` ConfigMap, 
  - which provides full admin access to the cluster.
  - later on, this admin user can add more user and add permission using k8s RBAC
  - **kubeconfig** has this user (federated user) only.
    - user is external entity, outside k8s, 
    - external oidc authenticated federated user
    - gets **authentication-token** from oidc.
    - token is validated, since `aws-auth` has entry. 

---
# B create new user (external OIDC / identity token based)
- reference : https://chatgpt.com/c/673940a9-d1cc-800d-a117-847107be2e53

## 1. create new `eks-user` (admin)
- Note: users === outside k8s user === represent aws `IAM user` or `IAM role`
  - of same AWS account
  - or cross AWS account
- create `aws-role-1`, attach policies/permission:
  - AmazonEKSClusterPolicy, AmazonEKSWorkerNodePolicy , AmazonEKSServicePolicy
  - inline : eks:AccessKubernetesApi, DescribeCluster, ListClusters, iam:gegetroltRole,PassRole
  - https://us-east-1.console.aws.amazon.com/iam/home?region=us-west-2#/roles/details/eks-cluster-role-1-for-federated-user?section=permissions - created manually.
- let's create new-user  (for `aws-role-1`)
- `first-admin-user` has to update `aws-auth` ConfigMap.
  - **kubectl edit configmap aws-auth -n kube-system**
```
mapRoles: |
  - rolearn: arn:aws:iam::123456789012:role/aws-role-1
    username: aws-role-1-user    <<<
    groups:
      - system:masters
      # - system:bootstrappers
      # - system:nodes
      # - system:node-proxier
```
- next login :
  - aws configure (with aws-role-1) :point_left:
  - update **kubeconfig**
  ```
    kubectl config set-cluster new-context-1 --server=https://<eks-cluster-endpoint>
    kubectl config set-context new-context-1 --cluster=my-cluster --user=aws-role-1-user
    kubectl config use-context new-context-1
  ```
---
## 2. create new `eks-user` (non-admin )  === cg
- same as above. but remove this `system:masters`
- add RBAC for a group (say : `developer-group-1`). check below
  - create `ClusterRole` and `ClusterRoleBinding` for this group. (for cluster-level resource)
  - create `Role` and `RoleBinding` for this group. (for ns-level resource)
  - in binding object > subject > kind: user, name: `aws-role-1-user`
```
# notice linking of group and user.
# this group is EKS specfic thing only 

mapRoles: |
  - rolearn: arn:aws:iam::123456789012:role/aws-role-1
    username: aws-role-1-user    <<<
    groups:
      - developer-group-1          <<<
```

```
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: developer-role-1
# edit rules - fine grain 
rules:
- apiGroups: [""]
  resources: ["*"]
  verbs: ["*"]
  labelSelector
    matchLabels:
        atm-id: "aa00003199"          <<<

---

apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: developer-role-1-binding
subjects:
- kind: Group
  name: developer-group-1  
  apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: ClusterRole
  name: developer-role-1
  apiGroup: rbac.authorization.k8s.io
  
```
---
# C  create new service Account
- create eks object yaml, inside ns.
- for permission to k8s-resource : `role and role-binding`
- for permission to aws-resource : `IRSA`
  - **annotate** service account with `aws iam role`.

