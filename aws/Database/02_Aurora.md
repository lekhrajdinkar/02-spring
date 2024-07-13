# Aurora
## Design
- engine : `Postgres` and `MySQL`
- 20% extra cost than RDS.
- has integration ML service : `SageMaker` and `Comprehend`
  - > usecase : fraud detection, ads targeting, sentiment analysis, product recommendations
    
- `scaling`: 
  - vertically : volumn-10GB to 128TB, ec2-i type, RAM
  - horizontal(automatically) : 1 to 15 Read replicas.
- `performance`:
  - AWS cloud optimized and claim `5x` Performance improvement.
  - master + 15 Read Replica, with fast replication.
- `Availability`
  - `6 copies` for data access 3 AZ
  - instant fail-over (<30s) + `self healing` from peer2peer replication.
  - ![img.png](../img/db/img.png)
  - ![img_2.png](../img/db/img_2.png)

- `snapshot`/backup + Recovery/`restore`
  - for `automatic` bkp , retention 1 to 35
  - for `manual`, retention - as long we want for maul backup.
  - `on-prem` MySQL/postgres DB --> create db-dumps( using `Percona XtraBackup`) --> place in S3 --> restore.
  - `cloning`:
     - faster than backup > restore
     - uses `copy-on-write` - use same volume + for new changes additional storage allocated and data copied to it.
    
  - > `Trick` : take snapshot and delete db. when you want to create new DB from snapshot. this will `save money`  

---            
## Global Aurora
- cross `region` replicas | single Database spans over multiple region.
- 1 Primary Region (read / write)
- Up to 5 secondary (read-only) regions, `replication` lag is less than `1 second`  **
  - Up to 16 Read Replicas per secondary region
- ![img_3.png](../img/db/img_3.png)

---
## more: 
  - Isolation and `security`
  - `Industry compliance`
  - Push-button `scaling`  
  - Advanced Monitoring
  - Routine `Maintenance` + Automated `Patching` with Zero Downtime
  - Backtrack: `restore` data at any point of time without using backups. (earliest :`5 mim ago`)

---
- demo:
```
- select engine
- select versions (so many available)
- template - prod (allow to configure everything)
    - admis + password
    - max i/o or standard
    - ec2 instance or serverless
    - choose : avialability - replica,etc
    - vpc, subnet, Ibv4
    - public access
    - sg
    - port
    - authentication
    - db name
    - ...

=== READY ====

- add more read replica
- add cross az replica 
- add region (global database)
- horizontal scaling policy (trigger : metric -CPU usage)
    - max 15 and min 1
    
```