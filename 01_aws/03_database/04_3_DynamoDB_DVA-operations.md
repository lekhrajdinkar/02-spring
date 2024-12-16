# A. DynamoDB - `Simple Operations` :books:
## 1. Read
### 1.1. GetItem
- Read based on Primary key
- Primary Key can be HASH or HASH+RANGE
- Eventually Consistent Read (default)
- Option to use Strongly Consistent Reads (more RCU - might take longer)
- **ProjectionExpression** 
  - to retrieve only certain attributes

### 1.2. Scan
- Scan the entire table and then filter out data
  - ProjectionExpression
  - FilterExpression
- Consumes a lot of RCU
- For faster performance, use **Parallel Scan**
- can use **limit and pagination**.
---
## 2. Write
### 2.1 PutItem
- Creates a new item 
- or fully replace an old item (same Primary Key)
- Consumes WCUs

### 2.2 UpdateItem
- Edits an existing item’s attributes 
- or adds a new item if it doesn’t exist
- use **Atomic Counters**

### 2.3 conditional Write
- Accept a `write/update/delete` only 
  - if conditions are met, 
  - otherwise returns an error
- Helps with **concurrent access** to items
- No performance impact
- ![img.png](../99_img/dva/db/02/img.png)
- **conditional**
  - `attribute_exists`
  - `attribute_not_exists`
  - `attribute_type`
  - `contains` (for string)
  - `begins_with` (for string)
  - ProductCategory `IN` (:cat1, :cat2) and Price `between` :low and :high
  - `size` (string length)

---
## 3. Query
- returns Item/s based on below **expression**:
  - **KeyCondition** : 
    - on partitionKey
    - on sortKey
  - **Filter** 
    - addition filter on other attributes.
- set **limit**
  - no of item
  - size. eg : 1 MB data.
- perform **pagination**
- **index** ?
  - local secondary ?
  - Global Secondary ?

---
## 4. Delete
### 4.1 DeleteItem
- Delete an individual item
- Ability to perform a conditional delete

### 4.2 DeleteTable
- Delete a whole table and all its items
- Much quicker deletion than calling DeleteItem on all items

---

# B. DynamoDB - `Batch Operations` :books:
- reducing the number of API calls
- done in parallel for better efficiency

### 1. BatchWriteItem
- Up to `25` PutItem and/or DeleteItem in one call
- Up to `16 MB` of data written, up to `400 KB` of data per item
- Can’t update items (use UpdateItem)
- error: **UnprocessedItems** for failed write operations 
  - exponential backoff 
  - add WCU

### 2. BatchGetItem
- Return items from one or more tables
- Up to `100 items`, up to `16 MB` of data
- Items are retrieved in parallel to minimize latency
- error: **UnprocessedKeys** for failed read operations 
  - exponential backoff 
  - add RCU