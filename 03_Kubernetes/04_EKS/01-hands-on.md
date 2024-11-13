# setup - machine + k8s software
- create EKS cluster
- set up master node - control panel 
  - install kube-apiserver
  - coreDNS
  - controllers
  - add ons:
    - metric server
    - logging server
- set up worker node
  - kubelet
  - kube proxy
  - docker / container-d
- setup network
- set up cloud service
  - ALB
  - EBS / EFS

---
## Option - 1 :: AWS - EC2
- spin up EC2 machine
- set up VPC
- software helps with this installation
  - `kops`
  - `kubermatics`

---
## Option - 2 :: AWS - managed service (EKS)
- EKS is cluster architecture.
- diff:
  - ECS is AWS specific concept and configuration
  - EKS follows kubernetes standards.
  - both solves same probolem in similar way.
- setup video:
  - https://www.udemy.com/course/docker-kubernetes-the-practical-guide/learn/lecture/22628019#overview
  - https://www.udemy.com/course/docker-kubernetes-the-practical-guide/learn/lecture/22628021#overview


### pre work:
- **permission** - create below roles:
  - `cluster-role-1` : 
    - standard policy
  - `node-group-role-1`
    - container registry policy
    - worker node policy 
    - CNI policy
- create VPC for EKS 
  - will get standard cloudformation template, use that for now.
- kubeCtl > add new context in kubeconfig file.
  - **aws eks update-kubeconfig --cluster cluster-1 --region r1** :point_left:
  
### steps
- create **cluster** with above VPC and role
  - it will add default ns.
- once created > **compute** tab > add Nodegroup with above role + vpc
  - choose instance-type and scaling.
  - it will also install k8s software needed for **worker node**.
- choose cluster type : **public and private**
- apply  deployment object.
- apply  service(LB type) object.
  - it will add load balancer > check ALB
- volumes (optional) : pv(efs) or sc + pvc
  - CSI - container storage interface) use to intergrate 3rd party storage on k8s.
  - check aws efs csi driver : https://github.com/kubernetes-sigs/aws-efs-csi-driver
  - install this driver :: **kubectl apply -k "github.com/kubernetes-sigs/aws-efs-csi-driver/deploy/kubernetes/overlays/stable/?ref=release-2.0"**
  - add EFS in same vpc (`fs-1`) + add security group to allow traffic with in VPC.
  - create PV and SC object
    ```
    # PV
    ...
    ...
    volumnMode: Filesystem
    storageClassName: efs-sc-1
    csi:
      driver: efs.csi.aws.com
      volumehandle:  fs-1  # id  of fs created above
    
    ---
    # SC
    ...
    ...
      name: efs-sc-1
    spec:
      provisoner: efs.csi.aws.com
    
    ---
    # PVC
    ...
    ...
      name: efs-pvc-1
    spec:
      storageClassName: efs-sc-1
      resources:
        requests:
          storage: 5Gi
    
    ---
    
    # pod
     volumns:
        - name:
          pvc:
            claimname: efs-pvc-1
    
     container:
      - ...
        ...
        volumnMounts:
          - name: 
            mountpath: /app/abc  
          
    
    ```
  