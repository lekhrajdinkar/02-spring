# run a Spring project inside pod
## using yml 
- https://medium.com/@javatechie/kubernetes-tutorial-run-deploy-spring-boot-application-in-k8s-cluster-using-yaml-configuration-3b079154d232
- https://github.com/Java-Techie-jt/springboot-crud-k8s/blob/main/db-deployment.yaml
---
- kubectl **run** my-app image=
- kubectl **cluster-info**
---  

1. minikube start --driver=docker
2. minikube docker-env

3. Deployment object:
   - create deployment-spring.yml
   - https://github.com/lekhrajdinkar/02-spring/tree/main/k8s/yml/deployment-spring.yml
   - > kubectl create/apply/replace/delete -f deployment-spring.yml

4. check 
   - > kubectl get/describe deployments
   - > kubectl get/describe pods
   - > kubectl logs spring-k8s-7dc6f56947-gflfd   

5. Services / Expose application
   - create service-spring.yml
   - https://github.com/lekhrajdinkar/02-spring/tree/main/k8s/yml/service-spring.yml
   - > kubectl create/apply/replace/delete -f service-spring.yml
   - > kubectl get nodes -o wide : check node IP, 192.168.49
   - > kubectl get services : check port, 30837
   - >  hit : http://192.168.49.2:30837/spring
   - > **minikube** service <service-name> --url

---

```
kubectl apply -f service-spring.yml
service/spring-k8s-service created
---
kubectl get services

NAME                 TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes           ClusterIP   10.96.0.1       <none>        443/TCP          3d2h
spring-k8s-service   NodePort    10.102.17.189   <none>        8083:30837/TCP   17s
---
kubectl get nodes -o wide

NAME       STATUS   ROLES           AGE    VERSION   INTERNAL-IP    EXTERNAL-IP   OS-IMAGE             KERNEL-VERSION                       CONTAINER-RUNTIME
minikube   Ready    control-plane   3d2h   v1.30.0   192.168.49.2   <none>        Ubuntu 22.04.4 LTS   5.15.153.1-microsoft-standard-WSL2   docker://26.1.1

---
minikube ip
192.168.49.2

--- 
didnot work
http://192.168.49.2:30837/spring

minikube service spring-k8s-service
```

---
## understand Yml
```
- `kind` <string>: Pod, Service, ReplicaSet/rs, Deployment
- `apiVersion` <string> :
  - apps/v1 --> Deployment, replicaSet
  - v1 --> pod, service 
- `metadata` <dictonary>:
  - **name** <string> : name-1 
  - **labels** <inner dictonary> :
    - k:v
    - k:v
    - ...
- `spec` <dictonary> :
  - ...
  - ...
---
- POD spec
  - `containers`: <List>

---
- ReplicaSet/rs spec
  - `template` : <pod metadata + poc spec>
  - `replicas` : 2
  - `selector` >  what pods under it | matchLabels > k:v, ... 
    - it also manages pods which are not created as part of `template` section
    - with selector, we can select and include them.
    
---
- deployment spec : same as rs

---

```