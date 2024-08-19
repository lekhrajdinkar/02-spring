# SSA Discussion

## A. MyClothes.com (Stateful)
- has database for each user
- has user session data in shopping cart.
---
### `Problem-1`:  cart lost > user request goes to different ec2-i everytime. 
  - Solution-1 : use `ELB stickiness`.
  - Solution-2 : `client side cookies` to maintained cart. FE developer (make server stateless)
    - client side cookies can alter by hacker
    - cannot store large-dataset
---
### `Problem-2 `: client side cookies can alter by hacker.
  - Solution-1 : use ElasticCache (server side cache). 
  - solution-2 : can use dynamoDB as well for temp record with TTL.
  - ![img.png](../99_img/ssa-discussion/11-2/img.png)

### `problem-3` : Persist user data
  - use RDS
  - also,update sg to allow restricted traffic.
---
### `problem-4` : read performance issue
  - add RDS Read replicas
  - use elastiCache to cache frequently access use data.
  - ![img_1.png](../99_img/ssa-discussion/11-2/img_1.png)

---
### `problem-5` : RDS and Elasti-cache : Availability / DR
  - use multi-AZ


