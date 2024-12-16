# A. DynamoDB - `components` :books:
## 0. **Table**
- PK : **partitionKey** , or
- PK : **partitionKey** + **Sortkey**
- ![img_2.png](../99_img/dva/db/01/img_2.png)
- Also, define **mode** for read/write operation:
  - `provisioned` - define **capacity** `RCU / WCU `
  - `onDemand` - uses `RRU / WRU` **requests**, internally. 2.5 times expensive.
  - can switch b/w modes, at any time :point_left:

## 1. **Record**
- or Item
- has attributes (`400 KB max`)

## 2. **Datatype**
- **Scalar Types** – String, Number, Binary, Boolean, Null
- **Document Types** – List, Map
- **Set Types** – String Set, Number Set, Binary Set

## 3. **TTL** 
- set expiration for record
- it will auto-delete and send event stream
- eg: 
  - TTL is `2 hr`
  - webUser --> session 2 hr --> session logout --> cleanUp his/her data after 2 hr.

## 4. **Write Capacity Units** (**WCU**)
- `1 WCU` == write `1 item`(`upto 1 KB`)/`sec`
- ![img_3.png](../99_img/dva/db/01/img_3.png)

## 5. **Read Capacity Units** (**RCU**)
- **2 types of read**
  - ConsistentRead == True
    - 1 RCU ==  1 **`Strongly` Consistent Read** of 1 item(`upto 4 KB`)
  - ConsistentRead == false (default)
    - 1 RCU ==  2 **`Eventually` Consistent Read** of 1 item(`upto 4 KB`)
- ![img_4.png](../99_img/dva/db/01/img_4.png)
  - because of replication lag, can be Strongly or Eventually consistent
- ![img_5.png](../99_img/dva/db/01/img_5.png)

---
## 6. Define **throughput** for **each table**
### **On-Demand Mode** 
- read/write operation, automatically scale up/down upto its max, with growing workloads
  - Read **Request** Units (RRU)
  - Write **Request** Units (WRU)
- for un-predictable workload.
- simplified billing but `2.5 times expensive`

### **Provisioned Mode** (default)
- for predicated workload
- can enable optionally, enable auto-scaling of WCU/RCU
- so we define **capacity** : RCU and WCU
- ![img.png](../99_img/dva/db/01/02/img.png)
- ![img_1.png](../99_img/dva/db/01/02/img_1.png)

### **ThrottleError**
- if capacity exceeded then `ProvisionedThroughputExceededException`
- **reason**
  - `Hot Keys `– one partition key is being read too many times (e.g., popular item)
  - `Hot Partitions`
  - `Very large items`, remember RCU and WCU depends on size of items
- **Solutions**:
  - retry with Exponential backoff when exception is encountered (already in SDK)
  - configure autoscale of WCU/RCU - [ min,max,desired ]
  - Distribute partition keys as much as possible
  - If RCU issue, we can use **DAX**

---
## 7. PartiQL
- SQL-compatible query language for DynamoDB - CRUD
- no joins
- Run PartiQL queries from:
  - web Console
  - **NoSQL Workbench** for DynamoDB
  - CLI/SDK