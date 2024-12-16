# DynamoDB (serverless)
## A. Intro
- **No Sql** 
  - unstructured
  - rapidly evolve schemas
  - All the data that is needed for a query is present in **one row**
  - don’t perform **aggregations** such as “SUM”, “AVG”, …
  - **scale horizontally** :point_left:
  
- **Fully managed** 
  - no maintenance/patching
  - no db admin
  - no provisioning db

- **high consistent performance DISTRIBUTED database** :point_left:
  - `single-digit millisecond performance`, at **any scale** 
  - `Millions of requests` per seconds
  - `trillions of row`
  - `100s of TB` of storage
  - Scales to `massive workloads`

- Allows event driven programming with **DynamoDB Streams** :point_left:

---
## B. Distributed DB (highly available)
### 1. Traditional relational DB:
- **Vertical scaling** (getting a more powerful CPU / RAM / IO)
- **`limited` Horizontal scaling** 
  - increasing Read Replicas.
  - but limited. eg max 16 read replica/s.

### 2 `Single-region` Table
- table data stored in multiple partitions.
- **hashing algorithm**( on PartitionKey) ==> decides which partition  to go.
- **partitionKey**
    - `unique`
    - `diverse`, to distribute data evenly on partition.  :point_left:
  
#### Partition
- ![img.png](../99_img/dva/db/01/img.png)
- dynamoDB
  - table-1
    - **partitions-1** (node-1) <--> **2 way multiple-AZs replication** <--> partitions-1(node-2)
      - both can Read and Write
      - no leader/primary concept.
    - **partitions-2**
    - ...
    - ...
    - scale out more partition/s
    
- Number of partitions
  - ![img_1.png](../99_img/dva/db/01/img_1.png)
    
### 3. `Global Table`
- table-1 (R/W) is `region-1`
- table-1 (R/W) is `region-2`
- `2 way replication` b/w both regions.
- ![img_3.png](../99_img/moreSrv/dynamo/img_3.png)
- Side Note: `Enable` **DynamoDB Streams** first,  helps to replicate data across replica tables in other AWS Regions

---
## C. DynamoDB streams
- some usecase:
    - `React` to changes in real-time by invoking `lambda`,` KCL-adaptor(Java-App) `
    - Real-time usage `analytics` : send stream to `AmazonShift`
    - Implement cross-region `replication`
- ![img_1.png](../99_img/moreSrv/dynamo/img_1.png)
- ![img_2.png](../99_img/moreSrv/dynamo/img_2.png)

---
## D. Storage 
- storage classes ( like in s3 ): 
  - **Standard**
  - **Infrequent Access (IA)** 
    - to save more cost

---
## E. Security
- Integrated with IAM for security, authorization and administration
- encryption at rest/fly

---
## F. DR
- **Automatic backup** for last `35 days`. 
- take ondemand backup for longer retention.
- Enable Cross-region copy. no performace impact/ downtime.
- PITR - point in time recovery
- `export` (json,ion) data --> S3.
- `import` (json,csv,ion) --> Dynamo DB
- ![img_4.png](../99_img/moreSrv/dynamo/img_4.png)

---
## G. Developer things :books:
### **Table**
- PK : **partitionKey** , or
- PK : **partitionKey** + **Sortkey**
- ![img_2.png](../99_img/dva/db/01/img_2.png)
- also, provision RCU/WCU

### 1. **record**
- attributes (`400 KB max`)

### 2. **Datatype**:
- Scalar Types – String, Number, Binary, Boolean, Null
- Document Types – List, Map
- Set Types – String Set, Number Set, Binary Set

### 3. **TTL** 
- set expiration for record
- it will auto-delete and send event stream
- eg: 
  - TTL is `2 hr`
  - webUser --> session 2 hr --> session logout --> cleanUp his/her data after 2 hr.

### 4. **Write Capacity Units** (**WCU**)
- `1 WCU` == write `1 item`(`upto 1 KB`)/`sec`
- ![img_3.png](../99_img/dva/db/01/img_3.png)

### 5. **Read Capacity Units** (**RCU**)
- **2 types of read**
  - ConsistentRead == True
    - 1 RCU ==  1 **`Strongly` Consistent Read** of 1 item(`upto 4 KB`)
  - ConsistentRead == false (default)
    - 1 RCU ==  2 **`Eventually` Consistent Read** of 1 item(`upto 4 KB`)
- ![img_4.png](../99_img/dva/db/01/img_4.png)
  - because of replication lag, can be Strongly or Eventually consistent
- ![img_5.png](../99_img/dva/db/01/img_5.png)

### 6. Define **throughput/capacity** for **each table**
- mode:
  - **Provisioned Mode** (default)
    - for predicated workload
    - can enable optionally, enable auto-scaling of WCU/RCU 
  - **On-Demand Mode** 
    - WCU/RCU automatically scale up/down upto its max, with growing workloads
    - for un-predictable workload.
    - simplified billing but `2.5 times expensive`

### 7. **ThrottleError**
- if capacity exceeded then `ProvisionedThroughputExceededException`
- **reason**
  - `Hot Keys `– one partition key is being read too many times (e.g., popular item)
  - `Hot Partitions`
  - `Very large items`, remember RCU and WCU depends on size of items
- Solutions:
  - retry with Exponential backoff when exception is encountered (already in SDK)
  - Distribute partition keys as much as possible
  - If RCU issue, we can use **DAX**

---
### 99. hands
```
- create db - no such thing :_)
- create table-1
    - choose : partition key (hash value used to retieve from table)
    - choose : sort key (optianal)
- capacity:
    - a. on-demand: simple, expensive 
    - b. Provision ** 
        - RCU
            - option-1 (scale-on) min-1 , max-100 , 70% utlization
            - option-2 (scale-off) capicity: 1
        - WCU
            - option-1 (scale-on) min-1 , max-100 , 70% utlization
            - option-2 (scale-off) capicity: 1
- indexing : pending
- Encryption (3 key options ) : dynamoDB-ownedKey, AWS-KMS, customerKey

- create item/record
    - record-1 : add attribute1,2,3,4    
    - record-2 : add attribute1,2
    - no schema :)    
```