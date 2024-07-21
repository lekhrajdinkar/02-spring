# D. on-prem tips

## 1. on-prem VM : import/export
- vm-1 is running on on-prem
- aws:ec2:`import` <-- vm-1
    - ec2-i1 running on cloud
- ec2-i1 --> aws:ec2:`export` --> on-prem(as vm-2)
    - vm-2 is running on on-prem

## 2. download AWS AMI to on-prem
- aws Amazon AMI --> download .iso file
- run AMI on on-prem , with hyper-v, virtual-box, etc.

---

# AWS service (overview)
## 1. Application discovery
- gather info from on-prem VM/server
- and create `migration plan`.

## 2. SMS : server migration service
- incremental migration of live server data

## 3. Migration Hub
- this help to track migration execution

## 4. DMS and SCT
- [DMS and SCT](./02_migration-2.md)