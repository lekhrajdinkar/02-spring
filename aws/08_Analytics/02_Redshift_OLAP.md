# Redshift
- **Not serverless**
- `OLAP` (online analytic processing) + Analytics and data warehousing. Not OLTP
- scale to `PB of data`

---
## Key feature
- `10x performance `than other data warehouses and athena
  - `parallel query engine` 
    - **Cluster** : `leader Node > compute Node`
    - `provision nodes(ec2-i)` in advance.
    - reserve instance for cost-saving
  - Columnar storage ? 
  - indexes
  - faster-joins and `faster-aggregation`
    - run complex SQL : jooin and aggregation

- `spectrum`
  - **Cluster** : leader Node > compute Node > `1000s of spectrum`
  - Redshift Spectrum, allows you to run queries on data stored in Amazon S3, without having to move that data into your Redshift cluster.
  - Analysts can use Redshift SQL queries to access both Redshift tables (recent data) and data in S3 (historical data) without moving data between the two, `enabling cross-referencing`
  
- `Integrated with glue` (crawler > catalog)
  
--- 
## Redshift : Load Data
- `source` --> Redshift (`query/analyze : complex SQL`) --> result --> `S3` and `Amazon QuickSight` (dashboard) and `Tableau`
- sources
  - kinesis Data stream --> `kinesis FireHore` --> Redshift
  - s3 --> Redshift
  - program(Java:SDK): `in-batches` --> Redshift
  - `glue` ETL  --> Redshift
  - ...

![img_2.png](../99_img/moreSrv/redshift/img_2.png)

---
## Redshift : DR
- single AZ by default
- cross `az`-replication : enable `Multi-AZ`  
- cross `region`-replication (better)
  - `incremental-snapshot`(only new change), in every 8 hr. retention: 35 days.
  - stored in s3.
  - restore snapshot/s into new region : **manually/automate**.
  - ![img_1.png](../99_img/moreSrv/redshift/img_1.png)

---
## Screenshot

![img.png](../99_img/moreSrv/redshift/img.png)
---
![img_3.png](../99_img/moreSrv/redshift/img_3.png)
