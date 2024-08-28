# EC2: Storage
## A. EC2 instant-store
- better iops, temp context cache | risk of data loss if h/w fails | manual backup.

---
## B. EC2: Storage: `EBS`
- deleteOnTermination[on/off]
  - primary - true
  - additional ebs - off
- Network drive, `same AZ`,
- only volume can be attached to single EC2-i only, multiple volumns assign to same ec2-i,
    - latency due to network.
    - `screenshot` / backups
        - archive (24-72 hrs),  or recycle bin(retention policy)
        - once archived, then create volume out it different AZ.
    - `encrypt` at rest, both - volume and screenshot using `KMS`
    - perform `capacity planning`, choose below Storage-groups: [ size, iops, throughput/tp]
        - `gp2` : `3 iops per GB` | size defines iops and tp | max- 16TB,    3k  iops, 125 Mbps
        - `gp3` : `flexible`. configure all 3 independently |  max- 16TB, 3k-16k iops, 1000 Mbps
            - > System boot volumes, Virtual desktops, Development and test environments
        - `io2` : 64K iops | max- 16TB
        - `io3` : 256k iops | max- 64TB | supports -` multi attach`.
            - > databases workloads
        - `HDD` :  max-500 iops | max-500 Mbps
            - > Big Data, Data Warehouses, Log Processing
        - `cold HDD` : max-250 iops | max-250 Mbps
            - > data that is infrequently accessed

---
## C. EC2: Storage: `EFS`
- uses `POSIX` file system + standard POXIS API
- `3x times expensive` than EBS, because:
  - `no capacity planning`, auto-Scale in Size(PB) and auto/manual adjust performance.
  - supports `multi-AZ`/Regional +  singleAZ
  - attach to multiple EC2 ( Linux based AMI only)
  - high performance : 3GBps/R , 1GBps/W
  - can save cost with `storage class`.

- `usecase` :  content management, web serving, data sharing, Wordpress, big data, media processing.
    
### storage class
- lifecycle policy to move between : `standard` >> Infrequent-Access/`EFS-IA`(after n1 days) >> `Archive`((after n2 days)) 50%
- same like s3.

### performance Mode
- `general-purpose`( default)
  - **low-latency** operations :)
  - lower throughput and is not ideal for highly parallelized big data processing tasks.
  
- `max I/O` 
  - Highly parallelized applications and **big data workloads** that require higher throughput.
  -  supports thousands of concurrent connections and higher I/O operations.
  - slightly **higher latencies** compared to the General Purpose mode :(
  
- `enhanced` : throughput scale regardless of size
  - `elastic` : auto-scale with the best performance. (R/recommended)
  - `provision` :  
    - manually configure throughput.
    - If your workloads require even higher and consistent throughput
    - allows you to specify the throughput you need, independent of the amount of data stored.
  
### Max limit
-  storage size : (auto scale to `Petabyte`-scale)
-  R/W max speed : `3GiB/s for R` and `1GiB/s for W`

### Security
- choose VPC/subnet >  add `sg`
- Encryption at rest using `KMS` + enable/disable automatic backup

### demo:
  ```
  - Create EFS `efs-1` + efs-sg-1
  - Ec2-i1 and i2 : launch instance > attach efs-1
  - choose mount location : /mnt/efs/fs1
  - aws automatically adds sg
      - ec2-i1-sg : inbound rule : Type:NFS, protocol:TCP, port:2049, source:efs-sg-1
      - similary outbound rule.
  - ssh to ec2-i1 and echo "hello" >  /mnt/efs/fs1/hello.txt
  - ssh to ec2-i2 and cat  /mnt/efs/fs1/hello.txt
  ```
