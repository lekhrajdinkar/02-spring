# StatefulSet

- same like Deployment
- additional points:
  - pod come up sequentially
  - no random pod name
  - each pod has index number as end.
  - first pod is always MASTER
  
- example : mysql-pod (DB replica)
  - ![img.png](../99_img/08/img.png)