# Kinesis 
- **real-time stream - collect+process**
  - app log
  - CW metric
  - web activity
---
## A. Kinesis Data Stream `KDS` (serverless)
- think of kafka.

- **serverless**
  - manages the infrastructure
  - storage
  - networking,
  - configuration needed to stream data.
  
### 1. key feature
- ingest data at scale 
- real-time processing

### 2. component
- ![img_4.png](../99_img/decouple/img_4.png)
- ![img.png](../99_img/decouple/img.png)

- **kineses stream**  === topic
  - retention : 1 - 365 days
- **shards** --> shard-1, shard-2, ...
  - shard count decides:
    - message/record throughput :` 1000 record/sec/shard` : if 6 shards => **6000 message/sec**
    - produce speed : `1 MB/sec/shard ` : if 6 shards => **6MB/s**
    - consume speed : `2 MB/sec/shard`  : if 6 shards => **12MB/s**
  - order : data in each shared is ordered.
  - one consumer per shard

- **record**  (message)
  - shard#,
  - Blob(data) 1MB-max
  
- **producer** : 
  - app(`SDK`/KPL)
  - kineses-Agent
  
- **consumer** :
  - app(`SDK`/KPL)
  - lambda
  - kDF (firehose)
  - KDA (analytics)
  
- ![img_2.png](../99_img/decouple/img_2.png)

### 3. capacity planning (stream>shard)
- **provisioned**
  - choose shard count needed.
  - cost - hourly `per shard`
  
- **on-demand**
  - auto-scale shards based on last 30 throughput peek history
  - cost 
    - hourly `per stream` :point_left: 
    - data in/out GB
  - Also, better `4000 record/sec/shard + 4 MB/sec/shard`


### 4. VPC endpoint
- ![img_1.png](../99_img/decouple/img_1.png)

### 5. hands on
- aws cli > produce cmd + consume cmd. (v:193)
- Security: IAM policy, Encryption (rest-kms, fly-tLS/ssl)

### 6. more
- use the `Enhanced Fanout feature`
  - provides each consumer application with its own dedicated throughput, up to 2 MB/second.
  - Multiple consumers can simultaneously process the same data stream without affecting each other's performance.
  - ![img_2.png](../99_img/dva/sqs/img_2.png)
- S3 --> `DMS` --> kinesis
  - ![img_1.png](../99_img/dva/sqs/img_1.png)

---
## B. Kinesis Data Firehose `KDF`
- **Near Real-time Delivery**
  - set buffer `size 0-900Sec`
- **serverless**
  - fully managed, 
  - no administration, 
  - auto scale, 
- **destinations**:
  - ![img_3.png](../99_img/decouple/img_3.png)

---
## C. Kinesis Data Analytic  `KDA`

---
## D. Kinesis Video Streams `KVS`
- skip

