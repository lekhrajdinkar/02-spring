# Resource Requirement

- by default, no limit --> any pod can use as much resource.
- pod > spec > containers > `resources`:
  - `request` (minimum)
    - cpu: 1  --> 1 count of CPU is equivalent to `1 vCPU`. That’s 1 vCPU in AWS.
    - memory: 256M
  - `limit` (maximum)
    - cpu: 3 --> will never go beyond. this.
    - memory: 500M --> but container can use more memory, beyon limit, if available on node, and eventually terminated with OOM.

- pod will be started at desirable-Node.
- if none of the node has enough resource, then it will error  out : 
  - eg: **FailedScheduling** No nodes are available that match all of the following predicates:: Insufficient cpu
- 4 behaviour
  - no request , no limit
  - no request , limit
  - request , limit
  - request , no limit  *** --> make sure has pod has some resource request set.

## LimitRange
- set limit at Namespace level, hence applied to all pod and need to define to each pod.
- this is effect only the newer pod.
```
apiversion: v1
kind" LimitRange
metadata:
  name: 
spec:
  limits:
    - type: container
      min:  === request
        cpu
        memory
      max:   === limit
        cpu
        memory 
      defaultRequest:  === request 
        cpu
        memory
      default:  === limit / like defaultlimit
        cpu
        memory
```
## ResourceQuota
- set limit on resource used by all pod in namespace, together.
```
apiversion: v1
kind" ResourceQuota
metadata:
  name:
  namespace:
spec:
  hard:
    requests.cpu
    requests.memory
    limit.cpu
    limit.memory
    
```

