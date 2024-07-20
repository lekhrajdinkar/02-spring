## A. Databases in AWS
  - `RDBMS` : 
    - RDS : 
    - Aurora : serverless, global DB, self-healing, continous s3 backup, PITR, 128TB auto-scale, OLTP
  - `ElastiCache`
  - `NO-SQL` : 
    - `Dynamo-DB` - serverless, globalDB, NoSQL
    - `Document-DB` (MongoDB)  : Does NOT have global DB, also not serverless(have ato do admin work)
    - `KeySpace` (Apache Cassandra)
  - `Neptune`(graph) :  NoSQL
  - `Time Series` : Amazon-TimeStream
  - `QLDB` quantum ledger db - financial txn, 1/10 time cheap and 1000x faster than rds.
  - more:
    - `Object Store` : S3-bucket, s3-glacier
    - `Data warehouse (Analytics)` : `Redshift`, `Athena`, `EMR`

---  
## factors involved in Choosing Right database
- `Data model`
  - RDBMS / NoSQL
  - Joins? Structured? Semi-Structured?
  - Strong schema? More flexibility?
  - Reporting? Search?
    
- `performance` : 
  - Throughput, 
  - Read-heavy, write-heavy, balanced workload
  - Latency requirements
  - Concurrent users?
  
- `Size` :
  - How much data to store and for how long? 
  - Will it grow? 
  - Average object size?
  
- `availability (global DB), replicate, DR Support, backup/recovery`
- `Security`
- `cost`
---