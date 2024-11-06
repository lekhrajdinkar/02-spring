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