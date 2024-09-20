## PODS
- containers are `encapsulated` inside pods
- horizontal `scale` up and down :
  - **add more pods** - y
  - add more container inside a pod - n
  - add more worker nodes - n
- can have `multi-container` pod-1 (rare use-case)
  - c1 - api
  - c2 - some helper api
  - both live/die together
  - comm : shares same name network-namespace and storage by-default.
    - no need manually setup-
    - that's one of the benefit with pod.

```
apiVersion
kind
metadata
  name:pod-1
  label
spec
  conatainers
    - name
      image
    - name
      image
```

- commands: 
```
- kubectl get/describe pod pod-1

- kubectl get pod <pod-name> -o yaml > pod-definition.yaml
  create yml out of existing pod
  
- kubectl edit pod <pod-name>
  only the properties listed below are editable:
    spec.containers[*].image
    spec.initContainers[*].image
    spec.activeDeadlineSeconds
    spec.tolerations
    spec.terminationGracePeriodSeconds

```