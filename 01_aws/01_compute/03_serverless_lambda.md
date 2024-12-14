# AWS : lambda 
## A. Function
- lambda initially was `FaaS`. Now serverless : `provision code/function` 
- use `lambda layer` for common code.
- deploy code without underlying infra
- `auto-scale with load` (parallel Lambdas, `max- 1000`)

- `network`:  
  - default : run in` aws owned VPC`, launch outside out VPC-1
  - can attach ENI with our VPC-1
  
- `cost` : per request +  compute-time(0-15 min) : `very Cheap`/`cost-efficeint`
  - First 1M free, then `20cent/million req`
  - First 400K GB-second free, then `$1/600K GB-second`
- `security`: Attach IAM role.

- `Configuration`:
  - RAM : `RAM 128 MB -10 GB`
  - timeout 0-15 min `15 min` / `900 Sec`
  - trigger :  [check integration](#b-integrated-with-services--triggers)
  - `handler` : file.method (eg: l_function.l_handler)
  - `runtime` : java 11, java21, etc
  - `permission` : attach one or more role/s (already allow CW)
  - `env var` : 4 KB
  - disk : `/tmp` , `512 MD to 10GB`

- deployment pkg size :
  - `50 MB`  compressed
  - `250 MB` code+dependency
  
-  logs : CW > log group
-  language supported : node, py, java, Golang, `C#/Ruby`, `Custom Runtime`.
  - java 11 or above : performance is 10x (free) - `SnapStart` feature
- `Lambda Container Image` --> run --> lambda Function
  - `base image` : lambda runtime API
  
---
## B. Triggers : Integrated with services 
- API-gateway/`REST` --> lambda
- S3, CW, DynamoDB-streams, eventBridge(trigger/schedule) > `event` > Lambda
- SQS/SNS > `Consumer/Subscriber` > lambda1/2
- Kinesis DataStream > `consumer` > Lambda
- client-req --> CloudFront ( `lambda@Edge`:customize req+some processing ) --> cf-origin-server
- IAM:`cognito` > Lambda
- ![img_1.png](../99_img/compute/lambda/img_1.png)

---
## C. Use case
- ![img_3.png](../99_img/compute/lambda/img_3.png)
- ![img_2.png](../99_img/compute/lambda/img_2.png)
- > connect lambda (launch in `vpc-1`) --> `RDS-proxy` : good practice  
![img_6.png](../99_img/compute/lambda/img_6.png)

---
## D. Screen shot:
- `AutoScale` : ![img_4.png](../99_img/compute/lambda/img_4.png)
- `SnapStart` : ![img_5.png](../99_img/compute/lambda/img_5.png)
