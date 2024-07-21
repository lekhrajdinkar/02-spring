- store: secret --> `secret-manager > secret`
- store: configuration --> `SSM > parameter-store`
---
# SSM : parameter-store
- type : 
  - `standard`(free) : 10k params, each param max size - 4KB, `no policy feature`
  - `advance` (5 cent/month) : 100K, 8k, attach `param-policies`, eg:
    - define TTL / auto delete parameter
    - restricted access to store.

- organize in structure/hierarchy pattern
- versioning enabled.
- encrypt it.

- `reference to secret `
  - > eg: /aws/`reference`/secretsmanager/secret_ID_in_Secrets_Manager
  
- `access aws public variable `
  - > eg: /aws/`service`/ami-amazon-linux-latest/amzn2-ami-hvm-x86_64-gp2

- has integration with:
    - CDK>`CloudFormation` : template can read configs.
    - `KMS` : security, encrypt/decrypt configs
    - `IAM` : enforce restricted access.
    - `eventBidge` : get events from store actions like : add,delete,update,access,etc
--- 
## use case
- #1. Store IAM policy inside store
- #2. Store secret also inside store
  - ![img.png](../99_img/security/acm/img.png)

## Demo-1
```
- create : /parent/child-1/param-1 - string
- create : /parent/child-2/param-1 - SecuredString
  - choose : kms-key-1
-  standard 4096 chars max, 4kb max

// ready

aws cli :
  - get-parameter /parent/child-1/param-1
  
  - get-parameter /parent/child-2/param-1
  - get-parameter /parent/child-2/param-1 --with-cecryption
  
  - get-parameter-by-path /parent
  - get-parameter-by-path /parent/child-1
  
  - access from lambda-1
    - set env var: child='child-2'
    - ssm=boto3.client('ssm', region1)
    - ssm.get_parameters(names=['/parent/'+ child +'/param-1'], WithDecryption=true)
    - failed:
    - fix: update lambda iam role:
      - allow read > ssm:path-/parent
      - allow kms : kms-key-1
```

