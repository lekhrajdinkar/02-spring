# Glue (Serverless , ETL)

## Usecase
- #1. transform data before loading to `redshift` data warehouse
![img_1.png](../99_img/moreSrv/analytic-1/img_1.png)
---
- #2. transform `csv to parquet` (columnar format,faster for analysis) --> `athena`
![img_2.png](../99_img/moreSrv/analytic-1/img_2.png)

---
## Glue Data catalog
![img_3.png](../99_img/moreSrv/analytic-1/img_3.png)

---

## GLUE : keypoint
- `**Glue Job Bookmarks**` : prevent re-processing old data
- `Glue Elastic Views`: virtual table.
- `Glue DataBrew`: clean and normalize data, using pre-built transformation
- `Glue Studio`: new GUI to create, run and monitor ETL jobs in Glue
- `Glue Streaming ETL` :
  - built on `Apache Spark Streaming`
  - compatible with 
    - Kinesis Data Streaming 
    - Kafka