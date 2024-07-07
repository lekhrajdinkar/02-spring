# K8s - Fundamental 
- k8s - 03 fundamentals : https://chatgpt.com/c/da40b952-dbd9-46a9-ad58-92c828a89118
- https://www.youtube.com/playlist?list=PLVz2XdJiJQxybsyOxK7WFtteH42ayn5i9

---

## A. Components:
- https://kubernetes.io/docs/concepts/overview/components/
- `Pods`: The smallest deployable units in Kubernetes that you create and manage.
-  `Services`: Abstract a set of pods and provide a consistent way to access them, even if the individual pods' IP addresses change.
-  `Nodes`: 
  - The worker machines in the Kubernetes cluster, which can run multiple pods.
  - typically runs on a separate virtual machine (VM), but this is not a strict requirement.
  - in AWS, each node is  seperate  VM for isolation.
  - VMs can be easily resized, moved, or replicated,
  - contains: kubelet, kube-proxy, container-runtime.

- hierarchy : 
  - `cluster` --> `node` --> `pod`,IP,workloads,posSpecYml/Json 
  - `container` --> `app`
- pod/node talk to each other using `Service` (has DNS).
- `Replica Set`:
  - one pod goes down another comes up from replica set.
  - ensures certain no. of pod running at specific time at all the time.
  - uses selectors(label query)
  - scale in/out replica count : `horizontal scale`.
  - Self-Healing: 
    - If any of the pods managed by a ReplicaSet are deleted or fail, the ReplicaSet controller will create new ones to maintain the desired number of replicas.

- `Deployment Object` 
  - for ReplicaSet (pods)
  - higher-level concept that manages ReplicaSets and updates on pods in declarative way:
    - command/cli 
    - yml file. 
- `Secret`:
  - Store DB config, password, etc outside SB app. then we dont need to build it again.
  - encrypted text, outside pod
- `Config map` : Plain text, outside pod.
- `ETCD` : 
  - key-Value database/store, 
  - 1MB max of a value.
  - store all its cluster data, such as cluster state, configurations, and metadata.
  
- `services`
  - An abstract way to expose an application running on a `set of Pods`, as a network service.
  - has DNS and static IP
  - 3 types:
    - `ClusterIP` : Exposes the service on a cluster-internal IP.
    - `NodePort` : Exposes the service on each Node's IP at a static port
    - `LoadBalancer` : Creates an external load balancer in the cloud provider (if supported) and assigns a fixed, external IP to the service.
  - Single Service for Multiple Pods (all having same image)
  - Separate Services for Different Pods (each having diff image)
  
- Add On:
  - `DNS Server` 
  - `metric Server`
  - Web based `UI`
  - Cluster level `logging`
  
---

##  cluster and node Components 
1. `master Node` (1) / control panel
- Checks memory, health, CPU, etc for each WorkerNode.
   - `Kube-API Server` : 
     - cluster-gateway
     - any request comes to cluster --> gateway --> WorkerNode --> ...
   - `Scheduler`
     - if WorkerNode-1 is 90%  and WorkerNode-1 is 30%, used.
     - then scheduler will assign new pods in WorkerNode-1
     - takes data from ETCD.
     - responsible for assigning workloads (pods) to nodes.
     - ensures workloads meet certain constraints and resource requirements.
   - `ETCD` :Persistence store.
   - `Controller manager`
     - runs `processes` in the background to regulate the state of the cluster.
     - Types/eg:
       - Node Controller
       - replication controller
       - Endpoints Controller
       - Service account and token Controller
   - `Cloud Controller Manager`
     - enabling Kubernetes to interact with underlying cloud provider APIs.
  
2. `worker Node` (Many)
   - Container `Runtime` 
     - to run containers (present inside Pods)
   - `Kubelet` 
     - agent running on each node.
     - masterNode::API-Server <-->  Kubelet-WorkerNode
     - Kubelet communicate with masterNode using API-server
   - `kube-proxy`
     - Network config
     - Network traffic Rule (from/to nodes) ingress/outgress

---
## B. Install
- https://kubernetes.io/docs/tasks/tools/

### B.1 install docker desktop
- docker Engine
- docker local repo
- gcr.io/k8s-minikube/kicbase:v0.0.44

### B.2 CLI : kubectl 
- https://kubernetes.io/docs/reference/kubectl/
- https://kubernetes.io/docs/reference/kubectl/quick-reference/
- curl.exe -LO "https://dl.k8s.io/release/v1.30.0/bin/windows/amd64/kubectl.exe
- `kubectl` [command] [TYPE] [NAME] [flags]
- .kube | KUBECONFIG env Var
- commands:
  - kubectl `version` --client --output=yaml
  - ** kubectl `cluster-info` dump
  - kubectl `run` --replicas=1000 xxxxxx
  - kubectl `scale` --replicas=1000 xxxxxx
  
> - aws eks --region region-code update-kubeconfig --name cluster-name
> - kubectl config use-context context-name

### B.3 kind or MiniKube
- minikube version: `v1.33.1`, download minikube.exe, set PATH, install docker (driver)
- Minikube automatically configures kubectl to use the Minikube cluster.
- purpose:
  - learn Kubernetes concepts and experiment with different configurations and deployments without the need for a full-fledged cluster
  - used in CI/CD pipelines for testing Kubernetes applications.
-  Run k8s cluster on your local machine | `single-node`
- `add-ons` : Ingress, Dashboard, DNS, Metrics Server,etc.
- configure : CPU and memory
- commands :
  - minikube `addons` enable/disable <addon-name>
  - minikube `service` <service-name>
  - minikube `status`
  - minikube `dashboard`
    - http://127.0.0.1:59962/api/v1/namespaces/kubernetes-dashboard/services/http:kubernetes-dashboard:/proxy/#/daemonset?namespace=default
  - ** minikube `start` --driver=docker | `stop` | `delete`
    - https://minikube.sigs.k8s.io/docs/drivers/
  - minikube version
  - ** minikube `docker-env`: allow miniKube to access local-docker-repo.
  

### run a Spring project inside pod
- minikube
  - minikube start --driver=docker
  - kubectl cluster-info
  - minikube status
  - kubectl get node
  - minikube docker-env
  - docker images
- kubectl
  - kubectl create `deployment` spring-k8s-deployment --image=com.lekhraj/spring:1.0.0 --port=8083
  - kubectl get deployments
  - kubectl describe deployment spring-k8s-deployment
  
  - kubectl get pods
  - kubectl logs <pod-name>
  
  - kubectl expose deployment spring-k8s-deployment --type=NodePort
  - kubectl get service

- minikube service <service-name> --url
- minikube dashboard

- Cleanup
  - kubectl delete service spring-xxxxx
  - kubectl delete deployment spring-k8s-deployment
  - minikube stop
  - minikube delete ?
  
- https://medium.com/@javatechie/kubernetes-tutorial-setup-kubernetes-in-windows-run-spring-boot-application-on-k8s-cluster-c6cab8f7de5a  

--- 
```
steps:

minikube start --driver=docker

* minikube v1.33.1 on Microsoft Windows 11 Enterprise 10.0.22631.3737 Build 22631.3737
* Using the docker driver based on existing profile
* Starting "minikube" primary control-plane node in "minikube" cluster
* Pulling base image v0.0.44 ...
* Restarting existing docker container for "minikube" ...
* Preparing Kubernetes v1.30.0 on Docker 26.1.1 ...
* Verifying Kubernetes components...
  - Using image gcr.io/k8s-minikube/storage-provisioner:v5
* Enabled addons: default-storageclass, storage-provisioner
* kubectl not found. If you need it, try: 'minikube kubectl -- get pods -A'
* Done! kubectl is now configured to use "minikube" cluster and "default" namespace by default

---
kubectl cluster-info

Kubernetes control plane is running at https://127.0.0.1:58455
CoreDNS is running at https://127.0.0.1:58455/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy

---
minikube status

minikube
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured
docker-env: in-use

---
minikube docker-env

SET DOCKER_TLS_VERIFY=1
SET DOCKER_HOST=tcp://127.0.0.1:58452
SET DOCKER_CERT_PATH=C:\Users\Manisha\.minikube\certs
SET MINIKUBE_ACTIVE_DOCKERD=minikube
REM To point your shell to minikube's docker-daemon, run:
REM @FOR /f "tokens=*" %i IN ('minikube -p minikube docker-env --shell cmd') DO @%i

---
docker images

REPOSITORY                                TAG        IMAGE ID       CREATED         SIZE
registry.k8s.io/kube-apiserver            v1.30.0    c42f13656d0b   2 months ago    117MB
registry.k8s.io/kube-controller-manager   v1.30.0    c7aad43836fa   2 months ago    111MB
registry.k8s.io/kube-scheduler            v1.30.0    259c8277fcbb   2 months ago    62MB
registry.k8s.io/kube-proxy                v1.30.0    a0bf559e280c   2 months ago    84.7MB

registry.k8s.io/etcd                      3.5.12-0   3861cfcd7c04   4 months ago    149MB
registry.k8s.io/coredns/coredns           v1.11.1    cbb01a7bd410   10 months ago   59.8MB
registry.k8s.io/pause                     3.9        e6f181688397   20 months ago   744kB
gcr.io/k8s-minikube/storage-provisioner   v5         6e38f40d628d   3 years ago     31.5MB

```