# K8s - Fundamental 
- k8s - 03 fundamentals : https://chatgpt.com/c/da40b952-dbd9-46a9-ad58-92c828a89118
- https://www.youtube.com/playlist?list=PLVz2XdJiJQxybsyOxK7WFtteH42ayn5i9
- https://kubernetes.io/docs/concepts/overview/components/

---

## A. Components:
- `container` are wrapped in function unit called `pods`.
- hierarchy : `cluster` --> `node` --> `pod`,IP,workloads,posSpecYml/Json --> `container` --> `app`
- pod/node talk to each other using `Service` (has DNS).
- `Replica Set` [ pod1, pod2, ... ]. one pod goes down another comes up from replica set.
- `Deployment Object` for pods and ReplicaSet
  - higher-level concept that manages ReplicaSets and updates on pods in declarative way:
    - command : `kubectl`
    - deployment.yml 
- `Secret`:
  - Store DB config, password, etc outside SB app. then we dont need to build it again.
  - encrypted text, outside pod
- `Config map` : Plain text, outside pod.
- `ETCD` : 
  - key-Value database/store, 
  - 1MB max of a value.
  - store all its cluster data, such as cluster state, configurations, and metadata.
- `services`
  - An abstract way to expose an application running on a set of Pods, as a network service.
  - has DNS.
  
- Add On:
  - `DNS Server` 
  - `metric Server`
  - Web based `UI`
  - Cluster level `logging`
  
---

##  cluster and node Components 
1. `master Node` (1)
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

### B.1 CLI : kubectl 
- https://kubernetes.io/docs/reference/kubectl/
- https://kubernetes.io/docs/reference/kubectl/quick-reference/
- curl.exe -LO "https://dl.k8s.io/release/v1.30.0/bin/windows/amd64/kubectl.exe
- `kubectl` [command] [TYPE] [NAME] [flags]
- .kube | KUBECONFIG env Var
- commands:
  - kubectl `version` --client --output=yaml
  - kubectl `cluster-info` dump

### B.2 kind or MiniKube
- minikube version: `v1.33.1`
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
  - minikube `start` | `stop` | `delete`
  - minikube version



