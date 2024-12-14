# AWS Lambda 
## A. Lambda:Function
- ![img.png](img.png)
- lambda initially was `FaaS`. Now serverless : `provision code/function` 
- connect lambda (launch in `vpc-1`) --> `RDS-proxy` : good practice

### 1 scaling
- auto-scale with load` (parallel Lambdas, `max- 1000`)
- ![img_4.png](../99_img/compute/lambda/img_4.png)

### 2 network  
- default: run in **aws owned VPC** :point_left:
- can attach ENI to run inside our **VPC-1**
  
### 3 pricing
- cost-efficient
- **no of call**: First 1M free, then `20cent/million req`
- **cpu usage**: First 400K GB-second free, then `$1/600K GB-second`

### 4 security
- Attach IAM role with fine grain access.

### 5 programming things
- **compute-time**: `0-15 min`
- **resource**:
  - RAM : `128 MB -10 GB`
  - disk
    - /tmp 
    - `512 MD to 10 GB`
- **lambda layer** for common code.
- **handler** : file.method (eg: l_function.l_handler)
- **language/runtime** : 
  - node, py, java, Golang, C#/Ruby, `Custom Runtime`.
  - java 11 or above : performance is 10x (free) - `SnapStart feature` :point_left:
  - ![img_5.png](../99_img/compute/lambda/img_5.png)
- **env var** : 4 KB
- **build pkg size** :
  - `50 MB`  compressed
  - `250 MB` code+dependency
-  **Monitor** : CW > log group
- **Lambda Container Image** 
  - run docker image in lambda Function
  - `base image` : lambda runtime API

### Triggers :green_circle: 
- API-gateway/`REST` --> lambda
- S3, CW, DynamoDB-streams, eventBridge(trigger/schedule) > `event` > Lambda
- SQS/SNS > `Consumer/Subscriber` > lambda1/2
- Kinesis DataStream > `consumer` > Lambda
- client-req --> CloudFront ( `lambda@Edge`:customize req+some processing ) --> cf-origin-server
- IAM:`cognito` > Lambda
- ![img_1.png](../99_img/compute/lambda/img_1.png)

---
## B. Architecture example
- ![img_3.png](../99_img/compute/lambda/img_3.png)
- ![img_2.png](../99_img/compute/lambda/img_2.png)
- ![img_6.png](../99_img/compute/lambda/img_6.png)
  
