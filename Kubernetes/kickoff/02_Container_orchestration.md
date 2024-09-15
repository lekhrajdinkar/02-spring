# k8s
- k8s - 02  Containers :  https://chatgpt.com/c/9836b4c7-2b23-497e-b06b-16885e3e18aa
- Containers - image, runtime, orchestration (ECS,K8s).
- https://kubernetes.io/docs/concepts/overview/
--- 
## A. Containers
  - `isolated environment` : physical machine > VM > Containers(host OS)
  - Containers are similar to VMs, but share the Operating System (OS) among the applications.
  - Therefore, containers are considered lightweight.
  - Similar to a VM, a container has its own filesystem, share of CPU, memory, process space, and more.
  - As they are decoupled from the underlying infrastructure, they are portable across clouds and OS distributions.


### Container images
  - eg: Docker image
  - image built on OS-1 as base, can be run my machine having window OS. docker desktop in between.
  - best suited for microservices/MS by providing `portability` and `isolated VM`
  - `executable package`, which confine the  `code,runtime and dependencies, env var+configFile` in a pre-defined format.
  - Composed of `multiple layers`, stacked on top of a base image.
  - Images are stored in `repositories`, which can be public or private. eg : Docker Hub, ECR
  - `versioned` using tags
  - Security:
    - Use `trusted base-images`
    - identify `vulnerabilities` in images and upgrade it. docker has built in scanner.
    - regularly update images to include security patches.
    
### Container runtime 
- eg: Docker Engine

---
## B. Container orchestrators
- For Distributed System | provides deployment patterns of container.
- maintain container at `runtime`
- use case:
  - can manually maintain a couple of containers.
  - or write scripts to manage the lifecycle of dozens of containers
  - orchestrators make things easier, when managing hundreds or thousands of containers.
- eg: ECS, Docker Swarm,Kubernetes/ k8s, marathon, etc.

### Kubernetes features (brief theory)
  - > can be deployed on cloud(ec2), on-prem(host), IaaS (EKS).
  - Automate the Container deployment at `scale`
    - Horizontal scaling : add up/down more pods.
  - `Automated Rollouts and Rollbacks`: 
    - Automatically roll out changes and roll them back if something goes wrong.
  - `Service Discovery and Load Balancing`: 
    - Automatically assigns stable IP addresses and a single DNS name() for a set of containers),
    - to facilitate load balancing and service discovery.
  - `Storage Orchestration`: 
    - Automatically mounts the storage system of your choice:
      - whether from local storage 
      - public cloud providers 
      - or network storage systems.
  - `Self-Healing`: 
    - Restarts failed containers, replaces and reschedules them, and kills containers that don’t respond to user-defined health checks.
  - `Secret and Configuration Management`: 
    - Securely stores and manages sensitive information such as passwords, OAuth tokens, and SSH keys.
  - `clustered systems` adv:
    -  increased performance, cost efficiency, reliability, workload distribution, and reduced latency.
  - Implement policies to `secure access` to applications/MS/service running inside containers.
  - Enable containers in a cluster to `communicate with each other` regardless of the host
  - `scheduling`
  - `Monitoring`
  - `Batch Execution`.
  - Pv4/IPv6 dual-stack

---

