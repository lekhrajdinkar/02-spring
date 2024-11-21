- https://chatgpt.com/c/22d9f577-17f2-4d43-9013-401b18ca58e0
--- 
# ACID principle
- All DB has underlying solution for ACID

## Atomicity
- start txn
- unit of work 
- commit txn

---
## Consistency
- pk
- fk
- constraints

---
## ISOLATION 
### write lock (always present)
- **problem** : `no concurrency at all`
  - txn1 , txn2 --> both are writing same record same time.
- solution is `write-lock`
  - txn-1 took w-lock > performing write
  - txn-2 waits
  - txn-1 done
  - txn-2 took w-lock
  
### read/write lock
- **problem** : `Dirty read`
  - txn1  --> writing same record same time.
  - txn3 --> reading
- solution is `read/write lock`
    - txn-1 took w-lock > performing write
    - txn-3 waits
    - txn-1 done
    - txn-2 took R-lock >> read
  
### version/sanpshot
- **problem** : `no repeating read`
  - txn-1 took w-lock > performing write
  - txn-2 waits
  - txn-1 done
  - txn-2 took R-lock >> Read 
  - txn-1 took w-lock > performing write AGAIN :left_point:
  - txn-2 should read it again and get updated value.
- solution is `version/sanpshot`
    - txn-2 will get latest from latest version
  
### range lock

  
---
## Durability
- data never crashes