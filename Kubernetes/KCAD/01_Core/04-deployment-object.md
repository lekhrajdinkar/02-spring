## Deployment Object
- deployment (has all revision history)
  - replicationSet-1 (revison=1)
  - replicationSet-2 (revison=2)
  - ...
  - replicationSet-n (current)
- concept:
  - deploy pod/s
  - upgrade (upgrade all pods together)
  - rollout (upgrade pods `one after another`)
  - rollback
  - pause and resume
- yaml is same as rs
  - just update kind to deployment object.

- some command 
``` 
  - kubectl get deployments
   
  - kubectl create deployment --image=nginx nginx
  
  - kubectl create deployment --image=nginx nginx --dry-run -o yaml > def.yaml
  
  - kubectl scale deployment nginx --replicas=4
  
  - kubectl edit deployment d1  :)
    - With Deployments, you can easily edit "any" field/property of the POD template. 
    - unlike pod edit, few feilds can edit.
    - Since the pod template is a child of the deployment specification, 
    - the deployment will automatically delete and create a new pod with the new changes. 
    
```
---
![img.png](../99_img/do/img.png)

![img_1.png](../99_img/do/img_1.png)

--- 
- controller written in `go`