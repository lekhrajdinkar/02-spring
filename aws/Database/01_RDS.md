# Database on AWS
## Option-1 
- provision Ec2
- install RDBMS and maintain it (os patching, security update, etc)

## Option-2 : AWS RDS
- `managed` DB service, no access/ssh to underlying Ec2 instance.
  - EBS(gp2/io1)
- Supported engine: 6 + 1 
  - Postgres, MySQL, MariaDB, Oracle, Microsoft SQL Server, IBM DB2
  - `Aurora` (AWS Proprietary database)
- Advantages of RDM:
  - `Auto-Scaling` ( thresold : <10%, last 5min, etc)
    - good for unpredictable workloads
    - vertical + horizontal
    - define Maximum Size.
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
  - option-1: add specified ec2-i, will add SG, etcautomatically
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