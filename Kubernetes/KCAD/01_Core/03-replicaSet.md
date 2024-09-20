## A. replication Controller
- `replicationController` is a older way
- `ReplicaSet` is a new way, with some diff.
- makes sure specified number of pods running all the time.
- span across node/s
  - ![img.png](../99_img/rs/img.png)
- yml (spec)
  - replicas : 3
  - nest the pod file in `template` section
  - it manages only pod defined in template
  - ![img_1.png](../99_img/rs/img_1.png)
  - ![img_2.png](../99_img/rs/img_2.png)

---

## B. Replica Set
- concept and yml --> almost same.
- it also manages, not defined in template section.
- thus having `spec.selector` (mandatory)

```
...
...
spec:
    ...
    ...
    selector: 
        matchLabels:
            type: front-end

## Some commands:
- kubectl get replicaset
- kubectl scale --replicas=6 -f replicaSet-definition.yml
- kubectl scale --replicas=6 replicaset replicaset-1

```
### label and selector
![img_3.png](../99_img/rs/img_3.png)

---

![img_4.png](../99_img/rs/img_4.png)