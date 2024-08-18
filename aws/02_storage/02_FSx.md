## Amazon FXs (serverless)
- RDS for serverless DB, similarly for fileSystem we have FXs.
- high performance FS / file-system, fully managed ; configure `multi-AZ` 
- access from `on-prem` (vpn-->internet or directConnect) or mount on ec2-i
- KMS encrypted.

---
### Windows FS
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
    - max : 10 GB/s
    
### Luster FS
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
  - ![img.png](../99_img/storage/more/img.png)

### NetApp ONTAP FS
- protocol : NFS, SMB, iSCSI
- use case : Move workloads running on `ONTAP or NAS`
- `auto-scaling`, compression
- Point-in-time instantaneous cloning
- `compatible` with lots of system.
- ![img_1.png](../99_img/storage/more/img_1.png)

### OpenZFS FS
- protocol : NFS (v3,4.1,4.2)
- use case : Move workloads running on `ZFS`
- compression.
- performance : millions of iops
- Point-in-time instantaneous cloning
- `compatible` with lots of system. same as netApp FS.


----
- side notes on FS protocol:
  - `SMB` is a `network` file-sharing protocol 
  - primarily used in Windows environments but is also supported on other platforms like Linux and macOS
  - `NTFS` is a file system developed by Microsoft for the Windows operating system. 
  - It’s used for storing and retrieving files on a hard drive or other storage devices
  - `iSCSI` is used to facilitate data transfers between storage devices and servers over `TCP/IP networks`.
  - `NFS` (Network File System) is a `distributed` file system protocol that allows users to access and share files over a network 