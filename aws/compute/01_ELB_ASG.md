## keys Terms
- `Availability` : multi AZ, prevent datacenter loss
- `Scalability`:
    - `horizon` scaling(elasticity) / `Distributed system`
        - scale in and out
    - `vertical` scaling
        - scale up and down.
- `load balancing` : gateway | forwards traffic to healthy servers. 

---
## ELB
- ELB(regional), forwards traffic to multiple ec2 in different AZ
- fixed hostname : `XXXX.region.elb.amazonaws.com`
- has :
  - health-check mechanism (/health) at `target-group` level
  - has `DNS` name
  - Security group : sg-lb-1
    - Also, link SG of ec2 instance with sg-lb-1 as source in traffic rule.
- Since `complex`, already configured and integrated with other AWS services.
  - route 53, ASG, EC2, Certificate manager, ECS, EKS
  - Cloudwatch, WAF, Global-Accelerator
- purpose:
  - gateway | forwards traffic to healthy servers.
  - separate public-traffic from private-traffic, can create public and private LB.
  - provide `SSL-termination` ?
  - Enforce `stickiness with cookies` ?
- Types:
  - CLS (deprecated)
  - `ALB` : operate at layer 7 : HTTp,HTTPS, websocket
  - network : operate at layer 4: TCP, UDP, TLS
  - gateway : GWLB, 2020

### A. ELB : ALB - Application LB (layer7)
- `client` (IP-1) --http-1--> `ELB` (add extra header in http : `X-forwarded-Proto`) --http-2--> `app-server`
- client >> ELB >> [ tg, redirect, fixed-http-response ]
- tg / `target groups`:
  - Types:
    - LB >> tg [EC2-I1, EC2-I3,...] : `VM`
    - LB >> tg [VM-1 [docker-1, docker-2, ...]] : `containers`
    - LB >> tg [lambda-1, lambda-2]
    - LB >> tg [ip address] : `on-prem server IPs`
  - Also, LB >> tg-1, tg-2, ... multilpe tg is possible.
  - routing/forwarding can happen at `route/path/url` ,` query-param `
    - eg: /url-1?`plateform=mobile` --> tg-1
  - demo:
      ```
      - Launch `ec2-i1` and `ec2-i2`, add sg-1 to both.
        - sg-1 : allow traffic ONLY from below `elb-sg-1` 
      - create target group - `tg-1` + /health/ + http:80
      - Creat ELB - elb-1, elb-dns-1
        - choose AZs
        - add `elb-sg-1` : all public traffic
        - add Listener & Routing :  
          - Listener-1::No-contion outside traffic on http:80  --> forward to --> `tg-1` 
          - Listener-1::consition-1 (priority-100) : path, header, queryparam, etc. [TRY] --> tg-?
          - ...
          - ...  
          - Note:rule with higestest priorty win  
        - hit dns-1
        - terminate ec2-i1 and hit elb-dns-1 again.
      ```

---
### B. ELB : NLB - Network LB (layer4)


---
### B. ELB : GWLB - gateway LB (?)


---
## AGS

