# Database on AWS

- summary:
  - Storage Auto-scaling (EBS volume size)
  - backup/restore : dumps>s3>restore, retention policy(1-35), manual dumps(always), 
  - `cloning` : EBS volume - clone
  - DR( multi-AZ and region ) : main-DB (Writer, only 1) > snapshot > restore-Standby  
  - performance Arch: `one` Write-Instance + `many` Read-Replica/s , `RDS proxy`
  - security: attach `Security group` on RDS instance, encryption at rest/fly, IAM 
  - usecase: RDBMS / OLTP 
---
## Option-1 : Ec2
- Provision Ec2
- install RDBMS and maintain it (os patching, security update, etc)

## Option-2 : AWS RDS
- `managed` DB service, no access/ssh to underlying Ec2 instance.
  - But `RDS custom` allow to access it  only for `SQL server` and `oracle` DB.
  - First disable automation mode, take snapshot, then access it.
- `EBS volume type` : `gp2` or `io1`
- `RDS instance size` : compute family size
- Supported engine: 6 + 1 
  - Postgres, MySQL, MariaDB, Oracle, Microsoft SQL Server, IBM DB2
  - `Aurora` (AWS Proprietary database, not Open source)
- Advantages of RDM:
  - `Auto-Scaling` : good for unpredictable workloads
    - `Read-replica Auto-scale` : 
      - Max 15, min 1.
      - bts use ASG and CW alarm ( metric: conn count, cpu utilization, read traffic, etc)
    - `storage Auto-scale`: Enable/Disable from console.
      - set max storage in GB/TB.
      - define `thresold` free space <10%, space runs last 5min, etc.
  - `DR`
    - Multi AZ-setup for DR.
    - master DB (az-1) --> `Sync replica` --> Stand-by DB (az-2) : no R/W
    - Automatic fail-over from master to standby.
    - just single click, can go from Single-AZ to multi-AZ RDS
      - bts : Single-AZ RDS --> screenShot (already taken) --> will be restored to Standby DB
  - `performance` 
    - `Main DB` + `Read replica/s` for improved read performance.
    - Up to 15 READ replica/s within Az, cross AZ, cross region.
    - main-DB --> `async replication (free within region)` --> Read Replicas
    - promote any READ replica as main DB later. hence for DR fail-over as well.
  - `Point in Time Restore`
    - Continuous backups and restore to specific timestamp
  - more:
    - `Dashboard`, `Analytics`  --> runs on READ replica
    - Automated provisioning, OS patching, etc
  - `Security`:
    - `At-rest` encryption:
      - Database master & replicas encryption using AWS KMS
      - If the master is not encrypted, the read replicas cannot be encrypted
      - To encrypt an un-encrypted database, go through a DB snapshot & restore as encrypted
    - `In-flight` encryption: 
      - TLS-ready by default, use the `AWS TLS root certificates` client-side.
      - use the same `domain-name-1` for both the certificate and the CNAME record in Route 53.
      - Export cert in ACM 
      - when create/modify RDS instance, configure it use custom  cname `domain-name-1`.
    - IAM Authentication: 
      - can use `IAM roles` to ec2-i, to connect to your database (instead of username/pw)
    - `Security Groups`: Control Network access to your RDS / Aurora DB
    - No SSH available, except on RDS Custom

  - `RDS proxy` : 
    - pools open connections.
    - reduces fail-over time by 66%
    - access privatey only
    - client --> RDS proxy --> RDs instance
    - ![img.png](../99_img/db/img_5.png)
    
- demo:
```
- create single DB RDB
- choose underlying ec2 type (memory optimzed), EBS volume
- DB admin + password + DB name
- backup/screenshot : 
  - enable + retention policy upto 35 days
  - backup window preferrence.
- enable STORAGE autoscaling, give maz size : 100 GB
- Connectivity : 
  - option-1: add "specified ec2-i", will automatically configure things (good for beginner)
  - option-2: Dont connect to Ec2-i
    - define VPC, subnet
    - allow public access
    - choose SG
    - port 
- Authentication : DB password or IAM
- Monitoring : Enable
- backup window pr
- Miantaincence window
- Enable deletion prevention 

 === READY to USE ===
 
- Check monitoring dashboard : CPU, Moemory, Connections, etc
- action:
  - create read replica
  - take Snapshot + migrate Snapshot + restore to point.
  - create read replica.
 
```
---
## Use case
- `RDS event ntf` > event catch > target : SNS, Lambda