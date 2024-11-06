# Athena (serverless)

- pay for data Scan : `$5/TB`
- run adhoc SQL query serverlessly
- parquet(columnar format)

---
## Athena : Load Data
- `source` --> Athena (`scan and query/analyze, data using SQL`) --> result --> `S3` and `Amazon QuickSight` (dashboard)
- sources:
  - `S3(object)`:csv,json,avro,`parquet(columnar format)`, 
    - vpc-logs,elb-logs, cloudtrail, goes to s3, can be analyzed.
    - organize data in S3 like `/year/month/day/hr/...`
    - so it will query specific object. fewer data will be scanned === cost reduce.
    - more cost saving : use `compressed` + `columnar` data
    - S3:object.csv -->`aws-glue` --> parquet
    
  - `on-prem/aws:database` (relational/NoSQL) --> `aws-glue` --> parquet
  
  - `kineses data steam`
  
  - `Data Source Connector`: AWS `Lambda` to run Federated Queries on RDS,CW,DynamoDB,etc

---
## Athena : integration with glue
- athena call `glue data crawler`
- crawler, crawls over above source/s, prepare some `metadata`
- and, create `Glue Data Catalog`
- Athena uses these catalog to run SQL.

---
## demo:
```
- bucket-1 >  enable s3-access log.
- copy location-1

athena:
    - create data_source-1
    - Query editor
        - run : create DataBase db1;
        - get query from internet, to DDL/DML to get se-access-log into tables.
        - edit query with location-1.
        - run query to perform ddl and dml, table created : bucket-1_logs
        - check L select * from database-1.bucket-1_logs limit 10.
        - query more standard sql and see report.

 more:
 - recent queries
 - saved queries - also encrypted result.   
```
---
![img.png](../99_img/moreSrv/athena/img.png)
---
![img_1.png](../99_img/moreSrv/athena/img_1.png)