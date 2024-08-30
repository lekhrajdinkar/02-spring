# KDA - Kinesis Data Analytics (serverless)
- Fully managed
- Automatic scaling

## A. Kinesis Data Analytics (SQL Application) / legacy
- SQL application --> run on KDA -->  real time analysis/process --> `stream`
- Source : `KDS/KDF` + also reference data from S3
- ![img_2.png](../99_img/moreSrv/analytics-2/img_2.png)

## B. Kinesis Data Analytics (Flink Application) / preferred
- flink application (more advance than SQL) --> run on KDA -->  analysis/process --> `Stream`
- Source : `KDS` + `MSK`
- ![img_3.png](../99_img/moreSrv/analytics-2/img_3.png)

## pricing
- no minimum fee or setup cost, 
- and you only pay for the resources your streaming applications consume