## A. Databases in AWS
  - `RDBMS` : RDS, Aurora
  - `NO-SQL` : DynamoDB, ElastiCache, `Neptune` (graph), `DocumentDB` (MongoDB), `KeySpace` (Apache Cassandra)
  - `Object Store` : S3-bucket, s3-glacier
  - `Data warehouse (Analytics)` : `Redshift`, `Athena`, `EMR`
  - `Time Series` : Amazon-TimeStream
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
  
- `availability, replicate, DR Support, backup/recovery`
- `Security`
- `cost`
---