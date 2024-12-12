- https://aws.amazon.com/blogs/
---
## AWS Services  for DVA

![img.png](../99_img/dva/00/img.png)

### Compute
- Amazon EC2
- AWS Lambda
- AWS Elastic Beanstalk
- Amazon ECS
- AWS Fargate
- Amazon Auto Scaling

### Containers
- Amazon ECS
- Amazon ECR
- AWS Fargate

### Storage
- Amazon S3
- Amazon Elastic File System (EFS)
- Amazon ElastiCache

### Databases
- Amazon RDS
- Amazon DynamoDB
- Amazon ElastiCache

### Networking & Content Delivery
- Elastic Load Balancing
- Amazon CloudFront
- Amazon Route 53

### Developer Tools
- AWS CodeCommit
- AWS CodeDeploy
- AWS CodeBuild
- AWS CodePipeline

### Monitoring & Observability
- Amazon CloudWatch
- AWS X-Ray

### Security, Identity, and Compliance
- AWS Identity and Access Management (IAM)
- AWS Key Management Service (KMS)

### Management & Governance
- AWS CloudFormation
- AWS CloudTrail
- Amazon EC2 Systems Manager

### Application Integration
- Amazon Simple Queue Service (SQS)
- Amazon Simple Notification Service (SNS)
- Amazon Simple Email Service (SES)
- Amazon API Gateway
- AWS Step Functions

### Analytics
- Amazon Kinesis

### Customer Engagement
- Amazon Cognito

---
## reference
- https://www.udemy.com/course/aws-certified-developer-associate-dva-c01/learn/lecture/19771482#overview
  - slides: https://courses.datacumulus.com/downloads/certified-developer-k92/
  - practice paper: https://www.udemy.com/course/aws-certified-developer-associate-practice-tests-dva-c01/
  -  If AWS Certified Solutions Architect course then watch only : 
  -  as of Dec 2024

- S4 : IAM
- s5 : EC2
- s6 : EC2:storage - EBS + EFS
- s7: [01_ELB_ASG.md](..%2F04_network%2F01_ELB_ASG.md)

```
Section 7 - AWS Fundamentals: ELB + ASG                     :: DONE
    Auto Scaling Groups - Instance Refresh

Section 8 - AWS Fundamentals: RDS + Aurora + ElastiCache    :: DONE
    ElastiCache Strategies
    Amazon MemoryDB for Redis

Section 9 - Route 53                                        :: DONE
    Routing Policy - Traffic Flow & Geoproximity Hands On

Section 10 - VPC Fundamentals                               :: DONE
    VPC Fundamentals - Section Introduction
    VPC, Subnets, IGW and NAT
    NACL, SG, VPC Flow Logs
    VPC Peering, Endpoints, VPN, DX
    VPC Cheat Sheet & Closing Comments
    Three Tier Architecture
    
Section 11,13,14 - "S3"                                 :: DONE (SAA)

Section 12 - AWS CLI, SDK, IAM Roles & Policies         :: DONE
    AWS EC2 Instance Metadata
    AWS EC2 Instance Metadata - Hands On
    AWS CLI Profiles
    AWS CLI with MFA
    AWS SDK Overview
    Exponential Backoff & Service Limit Increase
    AWS Credentials Provider & Chain
    AWS Signature v4 Signing

Section 15 - CloudFront                                  :: DONE
    CloudFront - Caching & Caching Policies
    CloudFront - Cache Behaviors
    CloudFront - Caching & Caching Invalidations - Hands On
    CloudFront - Signed URL / Cookies
    CloudFront - Signed URL - Key Groups + Hands On
    CloudFront - Advanced Concepts
    CloudFront - Real Time Logs

Section 16 - ECS, ECR & Fargate - Docker in AWS
    Amazon ECS - Rolling Updates
    Amazon ECS Task Definitions - Deep Dive
    Amazon ECS Task Definitions - Hands On
    Amazon ECS - Task Placements
    Amazon ECR - Hands On

And everything from Section 17 onwards

```