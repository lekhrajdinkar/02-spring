- https://chatgpt.com/c/da40b952-dbd9-46a9-ad58-92c828a89118
- https://www.youtube.com/playlist?list=PLVz2XdJiJQxybsyOxK7WFtteH42ayn5i9

---

## A. Minikube Components:
- https://kubernetes.io/docs/concepts/overview/components/
- - hierarchy :
  - `cluster` --> `node` --> `pod`,IP,workloads,posSpecYml/Json
  - `container` --> `app`

- `Pods`: 
  - The smallest deployable units in Kubernetes that you create and manage.
  - pod/node talk to each other using `Service` (has DNS).

- `Replica Set`:
  - one pod goes down another comes up from replica set.
  - ensures certain no. of pod running at specific time at all the time.
  - uses selectors(label query)
  - scale in/out replica count : `horizontal scale`.
  - span with cluster.
  - Self-Healing:
    - If any of the pods managed by a ReplicaSet are deleted or fail, the ReplicaSet controller will create new ones to maintain the desired number of replicas.

- `Deployment Object`
  - higher-level concept that manages ReplicaSets + `updates on pods`/rollup/rollback.
  
- `scheduler`
  - decides which node, a pod is assigned to.

- `Kube-Controller-Manager`
  - Runs various controller processes in the background to regulate the state of the cluster.
  - Node Controller, Replication Controller, Endpoints Controller,etc
  
- `Services`: 
  - Abstract a set of pods and provide a consistent way to access them, even if the individual pods' IP addresses change.
  
- `Nodes`: 
  - The worker machines in the Kubernetes cluster, which can run multiple pods.
  - typically runs on a separate virtual machine (VM), but this is not a strict requirement.
  - in AWS, each node is  seperate  VM for isolation.
  - VMs can be easily resized, moved, or replicated,
  - contains: kubelet, kube-proxy, container-runtime.

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

- ingress controller (nginx, aws:alb, etc) and ingress-object
- admission controller, ServiceAcct, role, role binding.
  
- Add On:
  - `DNS Server` 
  - `metric Server`
  - Web based `UI`
  - Cluster level `logging`
  
  
---
## B. Install
- https://kubernetes.io/docs/tasks/tools/

### B.1 install docker desktop
- docker Engine
- docker local repo / or,  dockerhub

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

### B.3 MiniKube
- gcr.io/k8s-minikube/kicbase:v0.0.44
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
  - minikube `service` <service-name> --> host services with external IPs.
  - minikube `status`
  - minikube `dashboard`
    - http://127.0.0.1:59962/api/v1/namespaces/kubernetes-dashboard/services/http:kubernetes-dashboard:/proxy/#/daemonset?namespace=default
  - ** minikube `start` --driver=docker | `stop` | `delete`
    - https://minikube.sigs.k8s.io/docs/drivers/
  - minikube version
  - ** minikube `docker-env`: allow miniKube to access local-docker-repo.
  
