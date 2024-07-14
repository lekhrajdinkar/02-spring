# VPC ( Virtual private cloud )
- single region -- > max: `5-VPC `
- single VPC --> max: `5-CIDR `
  - CIDR min ****/28 = 32-28 = 4 --> 2^4 = `16`
  - CIDR max ****/16 = 32-16 = 16 --> 2^16 = `65,536`
- VPC is `private` resource, referring only private IPs ranges.
- IMP : `Don't overlap CIDR`, if having multiple VPC **

---
## A. Key term
- CIDR/IP-range : `base-IP/fixed-bit (0-32)` 
- 0.0.0.0/0 : Any IP
- `Private IP`
  -  10.0.0.0 – 10.255.255.255 (10.0.0.0/8) : in big networks
  -  172.16.0.0 – 172.31.255.255 (`172.16.0.0/12`) : AWS default VPC in that range
  -  192.168.0.0 – 192.168.255.255 (192.168.0.0/16) : Home n/w
---

---
## B. default VPC : walkthrough
  - details:
    - CIDR-1 : `172.32.0.0/16`
    - CIDR-2,3,4,5 : can have
  - has/Contains:
    - `subnet`( found 4) : 4 az === 4 subnet ?
      - each has it available IP CIDR (total : 4091)
      - few IPS  are reserved.
      - `ACL`  (stateless / like sg)
        - Inbound rule
        - outbound rule
    - `route table`
      - Destination == target mapping
      - associated with all subnets 
    - `IGW`

---
## C. create new VPC
- region - us-west-2
- https://us-west-2.console.aws.amazon.com/vpcconsole/home?region=us-west-2#VpcDetails:VpcId=vpc-04ce2894d2f99bbb8
