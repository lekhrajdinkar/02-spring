# A. SCT
- schema conversion tool, can install on op-prem devices.
- for heterogeneous migration, only
- eg: fssrr : oracle --> SCT --> Postgres/aurora
- ![img.png](../99_img/dr/DR2/img.png)

# B. DMS  
- supports : `homo` + `hetero(with SCT)`
- concept
  - on prem db (large) --> offline : snowball --> aws cloud
  - on-prem-db --> `online : run DMS on EC2` --> aws cloud
---  
## source
  - `on-prem` : db ( Oracle, MS SQL Server, MySQL, MariaDB, PostgreSQL, MongoDB, SAP, DB2)
  - `azure` : sql-db
  - `aws`:
    - ec2 : db  ( Oracle, MS SQL Server, MySQL, MariaDB, PostgreSQL, MongoDB, SAP, DB2)
    - rds
    - aurora
    - s3
    - DocumentDB
---  
## targets
  - `on-prem` : db ( Oracle, MS SQL Server, MySQL, MariaDB, PostgreSQL, SAP)
  - `aws`:
      - ec2 : db ( Oracle, MS SQL Server, MySQL, MariaDB, PostgreSQL, SAP)
      - rds
      - aurora
      - s3
      - DocumentDB
      - neptune
      - redis
      - DynamoDB 
      - `analytic and stream kind`:
        - Kineses Data stream
        - Apache kafka
        - OpenSearch Service ?
        - Redshift ?
--- 
## DMS : Continuous replication
- use SCT and DMS
- also `enable multi-zone` on target db

![img_1.png](../99_img/dr/DR2/img_1.png)

![img_2.png](../99_img/dr/DR2/img_2.png)

--- 
## demo
```
// 1. DMS - `enpoints`
- endpoint-1 to connecto source DB
- endpoint-2 to connecto target DB

// 2. DMS - `replication-instance`
  - choose starting point:
    - discover and access
        - give roadmap, 
        - generate path in few hrs
    - convert
        - for hetero, use sct
        - takes weeks
    - migrate
        - choose:
            - instance-based (mamge/admins ec2) **
                - provision ec2 intance
                - DMS version
                - single-az/multi-az : az replication
                - storage for ec2
                - network
                - maintence window
            - serverless (no admin, migrated + replicates)
                - EASY

// 3. DMS `replication-task`
- create task-1
    - choose source endpoint
    - choose target endpoin
    - choose replication instance 
        - task setting : { json } - update it
           
```