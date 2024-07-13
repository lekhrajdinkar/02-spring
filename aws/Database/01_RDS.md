# Database on AWS

- Key topic/summary:
  - Auto-scaling : Hori and verti, policies 
  - backup/restore : dumps>s3>restore, retention policy(1-35), manual dumps(always), cloning(at volume)
  - DR : mainDB>snapshot>restore-Standby, RDS proxy 
  - performance Arch: W+R/s 
  - security: SG, encryption(rest/fly(TLS)) 
---
## Option-1 
- provision Ec2
- install RDBMS and maintain it (os patching, security update, etc)

## Option-2 : AWS RDS
- `managed` DB service, no access/ssh to underlying Ec2 instance.
  - But `RDS custom` allow to access it  only for MySQL and oracle DB.
  - First disable automation mode, take snapshot, then access it.
- EBS volume type : `gp2` or `io1`
- Supported engine: 6 + 1 
  - Postgres, MySQL, MariaDB, Oracle, Microsoft SQL Server, IBM DB2
  - `Aurora` (AWS Proprietary database, not Open source)
- Advantages of RDM:
  - `Auto-Scaling` : good for unpredictable workloads
    - `vertical`
      - define Max 15, min 1
      - `thresold` : <10%, last 5min, etc
    - Horizontal:
      - configure/update: each read replica manually : EBS volume, ec2-i family... , memory/RAM
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
      - TLS-ready by default, use the `AWS TLS root certificates` client-side
    - IAM Authentication: 
      - can use `IAM roles` to ec2-i, to connect to your database (instead of username/pw)
    - `Security Groups`: Control Network access to your RDS / Aurora DB
    - No SSH available, except on RDS Custom

  - `RDS proxy` : 
    - pools open connections.
    - reduces fail-over time by 66%
    - access privatey only
    - client --> RDS proxy --> RDs instance
    - ![img.png](../img/db/img_5.png)
    
- demo:
```
- create single DB RDB
- choose underlying ec2 type (memory optimzed), EBS volume
- DB admin + password + DB name
- backup/screenshot : 
  - enable + retention policy upto 35 days
  - backup window preferrence.
- enable autoscaling, give maz size.
- Connectivity : 
  - option-1: add specified ec2-i, will add SG, etc automatically (good for beginner)
  - option-2: Dont connect to Ec2-i
    - define VPS, subnet
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
 
```