# AWS Shield
- protect `DDoS attack` (many requests at the same time)

# AWS Shield : standard
- free, activated by-default.
- `protects` from `layer3/layer4` attacks : SYN/UDP Floods? , Reflection attacks?


# AWS Shield : Advance
- $3000/org, 
- 24/7 access to `DDoS response team` (DRP)
- `mitigate` from `layer7` attacks
  - Automatically creates/deploys `AWS WAF rules`.
- protects :
  - Amazon `EC2`, 
  - Elastic Load Balancing (`ELB`),
  - Amazon CloudFront:  `distributions` 
  - AWS Global `Accelerator`
  - `Route 53`
  - API gateway : NOOOOOO