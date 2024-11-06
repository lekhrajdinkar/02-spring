## key points
- container/s not completed isolated and sahre host kernal/os, but isolated based namespaces.
- all the process run by container/s, runs on the host but in their own `Namespaces`.
- **process isolation** : a container cannot see anything out of its namespace.
- `ps aux` show all process
  - a - all user
  - u - user-oriented format
  - u - includes daemon process
  - same process has diff pid in diff namespace. 
- by default, docker runs container with `root` user **on host** (with less Linux **capability**)
  - dockerfile > USER < userID >
  - docker run --user option
---
## Layered Arch

![img_3.png](img/crash-course/img_3.png)

![img_4.png](img/crash-course/img_4.png)

![img_5.png](img/crash-course/img_5.png)

---
## Registry

![img_1.png](img/crash-course/arch/img_1.png)

![img.png](img/crash-course/arch/img.png)

---
## Container Security:
  - --user < userID > 
  - --cap-add/drop < CAPABILITY >
  - Add these at:
    - container level :  ![img.png](img/imgg-1.png)
    - pod level :        ![img_1.png](img/imgg_2.png)
    - both present, container will override.

---
- pod-1
  - c1
    - process-1: port-1
    - process-2 : port-2
    - ...
  - c2
      - process-11 : port-11
      - process-22 : port-22
      - ...