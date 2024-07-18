# EKS

![img.png](../99_img/compute/eks/img.png)
---
- k8s (open source, cloud-agnostic)
  - `pod` 
  -` control panel/master node` 
  - `worker-node/s` === EC2 intance/s
    - managed : 
      - inbuilt `ASG` to scale nodes, register Node.
      - support od or spot
    - self-managed : 
      - register node manually
      - support od or spot
  - `service` 
  - `eks cluster`
- `on-prem(k8s)/other-cloud(k8s)` <--migrate-->  `EKS` :)
- use `EKS optimized AMI` to build container image.
- `Addon`:
  - CSI, Container Storage Interface :
      - ![img_1.png](../99_img/compute/eks/img_1.png)
  - 
  
---
- Demo:
```
1.  create cluster-1
  - choose K8s version
  - encrypt k8s secret with KMS : y/n
  - create IAM EKS-cluster-role-1 
    - attach inbuild "AmazonEKSClusterPolicy"
    - ...
  - n/w : 
    - vpc/subnet
    - ceate sg for cluster
    
2. update cluster with more detail
  - resource tab (k8s things) :
    - deployment object
    - service Object
    - pod
    - job
    - ...
    - ...
  - compute tab : 
    - Option-1 :: `Node/NodeGroup (bts: ec2-i)`
      - add Node (single ec2-i) 
      - or, add Node-Group (multiple Ec2-i, set - min, max, desired) 
      - IAM policy for ec2-i : attach "AmazonEKSWorkerNodePolicy", "AmazonEC2ContainerregistryReadOnly"
      - choose AMI ec2-i class, etc for ec2-i
    - Option-2 :: `fargate profile`
      - pending
      - ...
      
  - Add-on tab
    -  add EBS-CSI (Container Storage Interface) drive
    -  add VPC-CNI  
    - ...
    - ...
      
```


