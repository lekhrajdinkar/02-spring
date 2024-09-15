## ECS components

- `Clusters`
    - logical grouping of tasks or services.
    - Equivalent to `Cluster in Kubernetes`

- `Tasks`
    - A single running copy of a container defined by a task definition.
    - Equivalent to `Kubernetes Pod`

- `Task Definitions`
    - Blueprints for your application that specify the container images, CPU, memory, and other settings.
    - Equivalent in Kubernetes: `Pod Specification (PodSpec)`

- `Services`
    - Allows you to run and maintain a specified number of instances of a task definition simultaneously.
    - Equivalent in Kubernetes: `Deployment/ReplicaSet`

- `Container Instances`
  - Amazon EC2 instances registered to your cluster and used to run tasks.
  - Equivalent in Kubernetes: `Nodes`
  
- `Elastic Load Balancing (ELB)`
  - Distributes incoming application traffic across multiple targets.
  - Equivalent in Kubernetes: `Service (specifically, LoadBalancer type)`
  
- `Auto Scaling`
  - Adjusts the desired count of tasks in a service automatically based on criteria.
  - Equivalent in Kubernetes: `Horizontal Pod Autoscaler`
  
- `ECS Agent`
  - Software that runs on each container instance and communicates with ECS to start and stop tasks.
  - Equivalent in Kubernetes: `Kubelet`
  
- `ECS Fargat`
  - A serverless compute engine for containers that eliminates the need to manage EC2 instances.

