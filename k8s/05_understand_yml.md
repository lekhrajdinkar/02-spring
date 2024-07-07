## manifest Yml
### parent keys(4)
- `kind` : Pod, Service, ReplicaSet/rs, Deployment
- `apiVersion` :
  - Deployment, replicaSet : apps/v1
  - pod, service : v1
- `metadata`(2)
  - name :
  - labels : <dictonary> 
- `spec`: <dictonary>
  
---
- POD spec
  - `containers`: <List>

---
- ReplicaSet/rs spec
  - `template` : <pod metadata + poc spec>
  - `replicas` : 2
  - `selector` >  what pods under it | matchLabels > k:v, ... 
    - it also manages pods which are not created as part of `template` section
    - with selector, we can select and include them.
    
---
- deployment spec : same as rs

---



