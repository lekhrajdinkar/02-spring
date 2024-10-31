# Authentication
- ![img.png](../99_img/security/01/img.png)
- K8s cluster (`kube-apiserver`, running as system pod), will be accessed by :
  - `users (Admin / developer)` - we don't create them in k8s 
  - `SA` : we create these in k8s.
  - `end user` : out of scope. will be handled `inside application` deployed on k8s pod.

---
## Authentication mechanism
### Static password file  (basic Auth)
  - ![img_1.png](../99_img/security/01/img_1.png)
  - ![img_2.png](../99_img/security/01/img_2.png)
  - Modify the kube-apiserver startup options -
    - command section : add `--basic-auth-file=user-details.csv`
  - usage: curl -v -k https://master-node-ip:6443/api/v1/pods `-u "u1:p1"`
  - user-detail.csv --> pwd_1, username_1, userId, **groupId**
  - plain text file , hence **not** recommended. deprecated in Kubernetes version 1.19

### static token file
  - add `--token-auth-file=user-token-detail.csv` 
  - **token_1**, username_1, userId, groupId
  - usage: curl -v -k https://master-node-ip:6443/api/v1/pods `--header "Authorization: Bearer token_1"`
  - plain text file , hence **not** recommended. deprecated in Kubernetes version 1.19

### certificate
### external ID service - okta,LDAP.

---
