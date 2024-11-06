# OpenSearch
- load data from source and perform `search+analysis`
- Sources:
  - CloudWatch
  - dynamoDB stream
  - kinesis Data stream
  - ...

## Use case : dynamoDB Data stream
- dynamoDB --> search by `primary key` only.
- dynamoDB --> search by `any feild/attribute, partial match` : use OpenSearch
- ![img.png](../99_img/moreSrv/openSearch/img.png)

## Use case : CloudWatch
![img_1.png](../99_img/moreSrv/openSearch/img_1.png)

## Use case : kinesis Data stream
![img_2.png](../99_img/moreSrv/openSearch/img_2.png)