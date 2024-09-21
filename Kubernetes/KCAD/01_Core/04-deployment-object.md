## Deployment Object
- concept:
  - deploy pod/s
  - upgrade (upgrade all pods together)
  - rollout (upgrade pods one after another)
  - rollback
  - pause and resume
- yaml is same as rs
  - just update kind to deployment object.

- some command  
  - kubectl get **deployments**
  - kubectl get **all**  
  - kubectl create deployment --image=nginx nginx
  - kubectl create deployment --image=nginx nginx --dry-run -o yaml
  - kubectl scale deployment nginx --replicas=4
---
![img.png](../99_img/do/img.png)

![img_1.png](../99_img/do/img_1.png)