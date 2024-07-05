# run a Spring project inside pod
## using commands 

- minikube
  - > minikube start --driver=docker
  - kubectl cluster-info
  - minikube status
  - kubectl get nodes - o wide
  - > minikube docker-env
  - docker images
  - minikube ip
- kubectl
  - > kubectl create deployment spring-k8s-deployment --image=com.lekhraj/spring:1.0.0 --port=8083
  - kubectl get deployments
  - kubectl describe deployment spring-k8s-deployment
  
  - kubectl get pods
  - kubectl logs <pod-name>
  
  - > kubectl expose deployment spring-k8s-deployment --type=NodePort
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