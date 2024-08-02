## Amazon FXs (serverless)
- RDS for serverless DB, similarly for fileSystem we have FXs.
- high performance FS / file-system, fully managed ; configure `multi-AZ` 
- access from `on-prem` (vpn-->internet or directConnect)
- KMS encrypted.


### windows FS
  - supported protocol : `SMB & NTFS` smb-server message block
  - storage option : SSD and HDD.
  - DR : s3 backup
  - mount on ec2-i (OS : windows + `Linux` too)
  - integrate with 
    - `ms AD` - self or AWS managed ms AD.
    - `ACLs`
    - `ms DFS` : group multiple FS 
  - performance:
    - millions of iops
    - 100s PB data
    - 10 GB/s
    
### luster FS
- luster : linux + cluster
- parallel DFS
- use case : HPC, ML, video process, modelling, etc
- storage option : SSD and HDD
- `integrate with S3` : can R/W from S3 as FS <<<
- performance:
  - millions of iops
  - 100 GB/s
- deployment option:
  - `scratch` : short term storage, 6x faster, `no data replication`
  - `persistent`: Long term storage: data replication in same AZ
  - ![img.png](img.png)

### NetApp ONTAP FS
- protocol : NFS, SMB, iSCSI
- use case : Move workloads running on `ONTAP or NAS`
- `auto-scaling`, compression
- Point-in-time instantaneous cloning
- `compatible` with lots of system.
- ![img_1.png](img_1.png)

### OpenZFS FS
- protocol : NFS (v3,4.1,4.2)
- use case : Move workloads running on `ZFS`
- compression.
- performance : millions of iops
- Point-in-time instantaneous cloning
- `compatible` with lots of system. same as netApp FS.