# kubeConfig File
- read by `kubectl`
- present at $HOME/.kube/config (default file)
  - **kubectl config view**
  - can have more file
  - kubectl .... **--kubeconfig=new-file**
- has 3 section
  - `cluster` : 
    - --server my-kube-playground:6443 (**cluster-1**)
    - --certificate-authority cacert.crt
  - `context`  --> which user will connect to which cluster, that mapping
    - **admin_1** will connect to **cluster-1**
  - `users` : admin_1
    - --client-key            admin_1.key
    - --client-certificate    admin_1.crt
  - Note: above 4 option/s --xxxx, get attached to every kubectl command we make.

## ScreenShots
- ![img.png](../99_img/security/02/img.png)
- ![img_1.png](../99_img/security/02/img_1.png)
- add more entries:
  - ![img_2.png](../99_img/security/02/img_2.png)
  - there are 3 contexts, make one default.
    - after kind, add `current-context: dev-user@google`
- can also define `namespace` in context:
  - ![img_4.png](../99_img/security/02/img_4.png)
- `certificates`
  - can put base64 encoded certificate data, as well
  - ![img_5.png](../99_img/security/02/img_5.png)

--- 
## Commands
- kubectl config **view**
- kubectl config **use-context** prod-user@production-cluster
- kubectl config -h
  - ![img_3.png](../99_img/security/02/img_3.png)
