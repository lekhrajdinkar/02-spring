# AWS Global accelerator (global srv)
-  global service and cannot be deployed to a specific region

## key term
- `Uni-cast IP` : one ip assigned to `one` server
- `Any-cast IP` : same ip assigned to `multiple` server
  - but, client req goes to geo close loc.
- `accelerator` : server with anyCast IP and uses AWS privateLink.

## problems
### problem-statement - 1 (hopping)
- `apb-1(api-1)` is running on region-1 `mumbai`
  - client in `USA` --> hops overs multiple server/s s1 > s2 > s3 > s4 > s4 --> to reach `alb-1`
  - client in `europe` --> hops overs multiple server/s s1 > s2 > s3 --> to reach `alb-1`
  - client in `nepal` --> hops overs few server/s > s1 --> to reach `alb-1` 
- this creates `high latency` + `loss of n/w risk`.

### problem-statement - 2 (latency)
- `alb-1(api-1)` is running on region-1 `mumbai`
- `alb-2(api-2)` is running on region-2 `europe`
- client in `USA` --> want to connect to closer region-2 europe

### Solution of above problem/s
- `alb-1(api-1)` is running on region-1 `mumbai`
- create CF-acce-1(`anycast-ip-1`) with endpoint group(alb-1,alb2) 
- hit ip-1 --> send traffic to its closet edge loc first --> `privateLink` + `intelligent-routing`(short-Path, close region) -->  edgeLoc close to destination --> `alb-1/2`
  - client in `USA`    --> CF acce-1 (`anycast-ip-1`)    --> to reach `alb-2`
  - client in `europe` --> CF acce-1 (`anycast-ip-1`)    --> to reach `alb-2`
  - client in `nepal`  --> CF acce-1 (`anycast-ip-1`)    --> to reach `alb-1`
- anycast-ip-1 : `fixed IP`,  does not support dynamic IP addresses.

- AWS `Global accelerator` help to do same.
  - create anycast for my `set of alb/app`.
  - bts, leverage aws `edge-location` with `privateLink` to reach out closest target server fast.
  - benefit:
    - low `latency`, consistent performance.
    - fast `failover`, 
    - integrated with `AWS-sheild`, thus provides `DDoS protection`
- demo:
```
- launch ec2-1 in region-1 (us-east-1)
- launch ec2-2 in region-2 (us-west-1)
- launch alb-1 in region-2 with ec2-3
- create accelerator-1 : dn-1
- create Listener/s :
    - listener-1 : 
        - TCP:80
        - endpoint-group: 
            - endpoint-1 : ec2-1 
            - endpoint-2 : ec2-2
            - endpoint-3 : alb-2
            - endpoint-4 : static-ip 
        - setup health as well, for each endpoint.
- hit dn-1 url:
    - goes to us-east-1,ec2-2 everytime, since iam in CA,USA

// failover
- make ec2-1 fail, update sg : deny traffic.
- -hit dn-1 url
    - goes to us-east-1,ec2-1 now
    
```
---
## Screenshots:
![img.png](../99_img/CF/ga/img.png)
![img_1.png](../99_img/CF/ga/img_1.png)
