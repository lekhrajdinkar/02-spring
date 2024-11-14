# Cluster
## cg - MCP cluster
- multi tenant(shared) kube env in 2 aws region. Dedicated AWS account - **mcp-aws**
- Application team role :  namespace, deployment, services, ingress-rule, DR
- platform-team roles :
  - cluster auto-scale
  - ingress controller (eg: nginx)
  - agents - security, logging, monitoring, reloader-rollout on configmap chnages,
  - nodes -patching
  
### On board 
- for on-boarding, provide 
    - `cluster name`: mcp-etc|im-use1|usw2-dev|qa|prod-ns-01
    - `role` --> our AWS account: `Broad-access-role`
- actions:
  - `action-1` : adds SSM parameter store:
    - **cluster_info**
    - **kubeconfig** (cluster CA cert, user, context)
      - user-1 > execute aws command : aws eks get-token --cluster --region 
      
  - `action-2` : `cluster` - already created, will be shared.
    
  - `action-3` : `service-account` - already created, same as cluster name.
    - used to for **authentication** for kubektl
    - mcp-etc|im-use1|usw2-dev|qa|prod-ns-01
    - check kubeconfig file at bottom.
    - **aws eks get-token** --> gets token from IAM:identity provider for sa.
    
  - `action-4 `: 
    - **aws eks describe-cluster** --name <cluster-name> --region <region> --query "cluster.identity.oidc.issuer" --output text
      - OIDC provider is automatically enabled when you create an EKS cluster.
    - add OIDC provider in IAM:identity provider
      - `issuerId` - https://oidc.eks.us-west-2.amazonaws.com/id/eks-cluster-id
      - `audience` - sts.amazonaws.com
    - used to authenticate both sa:
      - 1 mcp-etc|im-use1|usw2-dev|qa|prod-ns-01 (above) - for kubectl
      - 2 sa(pod), will be created in future. check developer section for more.
    
  - `action-5` : Broad-access-role provided, must be used for to provide **cross account access** or something else ?
    - mcp-aws::`eks-role`
      - inline-policy --> "eks:*" + access to specific k8s-namespace
      - **trusted-policy** 
    ```
    ...
    # trusted-policy
    
    "Principal": {
       "AWS": "arn:aws:iam::aws-1:role/Broad-access-role"
    },
    "Action": "sts:AssumeRole"
      
    # get AWS token
    aws sts assume-role --role-arn arn:aws:iam::<aws-acct-for-eks>:role/<eks-role>
    export AWS_ACCESS_KEY_ID=<Access-Key>
    export AWS_SECRET_ACCESS_KEY=<Secret-Key>
    export AWS_SESSION_TOKEN=<Session-Token>
      
    # Update your "kubeconfig" to interact with the EKS cluster in Account 2
    aws eks update-kubeconfig 
      --name <cluster-name> 
      --role-arn arn:aws:iam::<aws-acct-for-eks>:role/<eks-role>
      --region us-west-2
    ```  
---
### developer actions       
- add **namespace**:
  - atm_id-dev|qa|prod-description
  - add tags.

- next, create service account - sa1 (for pod exec)
  - annotate sa object with : `eks.amazonaws.com/role-arn:` **pod-exec-iam-role:arn**
  - add **inline-policy** to role - > access s3,sqs, etc
  - add **trusted-policy**
```
{
  "Effect": "Allow",
  "Principal": {
    "Federated": "arn:aws:iam::<account-id>:oidc-provider/oidc.eks.<region>.amazonaws.com/id/<eks-cluster-id>"
  },
  "Action": "sts:AssumeRoleWithWebIdentity",
  "Condition": {
    "StringEquals": {
      "oidc.eks.<region>.amazonaws.com/id/<eks-cluster-id>:sub": "system:serviceaccount:<namespace>:<service-account-name>"
    }
  }
}
      
```
- deploy pods/deploymnet with service-account sa1
  - pod sa will assume pod-exec-iam-role.
- use secret/configMap + aws secret manager
- use ingress

---
- **kubconfig**
```
apiVersion: v1
kind: Config
clusters:
  - name: mcp-im-usel-dev-ns-01
    cluster:
      server: https://kube-admin
      cacert-data: b64....
contexts:
  - name: mcp-im-usel-dev-ns-01
    context:
      cluster: mcp-im-usel-dev-ns-01
      user: mcp-im-usel-dev-ns-01
current-context: mcp-im-usel-dev-ns-01

users:
  - name: mcp-im-usel-dev-ns-01
    user:
      exec:
        apiVersion: client.authentication.k8s.io/v1beta1
        command: aws
        args:
          - eks
          - get-token
          - --region
          - us-east-1
          - --cluster-name
          - mcp-im-usel-dev-ns-01
```        

  

