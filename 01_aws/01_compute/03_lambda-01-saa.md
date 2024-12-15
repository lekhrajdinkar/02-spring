- https://docs.aws.amazon.com/lambda/latest/dg/welcome.html
--- 
# AWS Lambda 
## A. Lambda:Function
- ![img.png](../99_img/dva/l/01/img.png)
- lambda initially was `FaaS`. Now serverless : `provision code/function` 
- lambda@edge - globally service. :point_left:
  - author: **us-east-1**
  - replicated to edge location from author.
- invoke:
  - by services(eg: s3 trigger lambda)
  - by SDK/CLI
    - `--invocation-type Event` for making async call from cli

### 1. Scaling
- auto-scale with load` (parallel Lambdas, `max- 1000`)
- ![img_4.png](../99_img/compute/lambda/img_4.png)

### 2. Network :o:
- default: run in **aws owned VPC** (has public internet access) 
- run inside our **VPC-1** and subnet
  - attach **ENI** to lambda
    - ENI has SG
      - define ingress/egress.
  - deployed in `private subnet` --> don't have internet access
  - deployed in `public subnet` (igw attached)
    - still **don't have internet** access by-default :point_left: :point_left:
  - Get internet access with **NAT**
    - ![img.png](../99_img/dva/l/01/img-vpc-1.png)
    - deploy lambda in private subnet
    - update rtb, to route traffic 0.0.0.0.\0 traffic to NAT gateway
    - this is the only way.
  
### 3. Pricing
- cost-efficient
- **no of call**: First 1M free, then `20cent/million req`
- **cpu usage**: First 400K GB-second free, then `$1/600K GB-second`

### 4. Security
- Attach IAM role with fine grain access to lambda. eg:
  - cw:log-group
  - sqs:poll (event source mapping)
  - ...
- **lambda resource based policy** 
  - who can invoke the Lambda function and under what conditions 
  - eg: allow S3 bucket:

---
### 5. programming things :book:
#### **5.1. Basic**
- **compute-time**: `0-15 min`
- **resource**:
  - RAM : `128 MB -10 GB` + improves network as well
  - disk
    - /tmp 
    - `512 MD to 10 GB`
- **lambda layer** for common code.
- **handler** : file.method (eg: l_function.l_handler(`event`,`context`))
  - **context** - metadata about the Lambda function execution environment.
  - **event** - contains the input data for the Lambda function.
  - sample: [03_lambda-dva-03-context+event.md](03_lambda-dva-03-context%2Bevent.md)
- **language/runtime** : 
  - node, py, java, Golang, C#/Ruby, `Custom Runtime - rust/golang`
  - java 11 or above : performance is 10x (free) - `SnapStart feature` :point_left:
  - ![img_5.png](../99_img/compute/lambda/img_5.png)
- **build pkg size** :
  - `50 MB`  compressed
  - `250 MB` code+dependency
  
---  
#### **5.2. ENV var** 
- `4 KB`
- can encrypt them as well :point_left:
- sample py code:
  ```
  import os;
  os.getenv("ENV_VAR_1")
  ```
---  
#### **5.3. Monitor** :o:
- **inbuilt metric**
  - `iterator-age`: can check **stream** iterator/offset/sequence :point_left:
  - `concurrent-execution`
  - `Throttle`
  - `Async delivery failure`
  - ...
- **log-group** : /aws/lambda/lambda-1/
- **traces** :point_left:
  - Enable **Active Tracing**, to run the **X-Ray daemon**
  - add this to Lambda-role : `AWSXRayDaemonWriteAccess`
  - Environment variables to communicate with X-Ray
    - `_X_AMZN_TRACE_ID`: contains the tracing header
    - `AWS_XRAY_CONTEXT_MISSING`: by default, LOG_ERROR
    - `AWS_XRAY_DAEMON_ADDRESS`: the X-Ray Daemon IP_ADDRESS:PORT
  - Use AWS X-Ray SDK in Code
    - use these env var and write trace.
      
#### **5.4. Lambda Container Image** 
- run docker image in lambda Function
- `base image` : lambda runtime API

---
## B. integration with other services :green_circle: 
- **lambda trigger** patterns:  :point_left:
  - **Event source mapping** 
  - **Synchronous** 
  - **A-synchronous**
  - details: [03_lambda-dva-02-trigger.md](03_lambda-dva-02-trigger.md) for details

- all common eg:
  - **API-gateway** (REST) >> lambda
  - **ALB** >> target-group-1:lambda :o:
    - ![img_1.png](../99_img/dva/l/01/img_1.png)
  - **S3:objectcreate,etc** >> lambda :o:
  - **CW:log-event||loggroup-subscription-filter** >> lambda (log-processing)
  - **DynamoDB-streams** >> lambda
  - **SQS/SNS:consumer** > Consumer/Subscriber > lambda/s :o:
  - **KDS:consumer** >> Lambda/s
  - web-client-req --> **CloudFront** ( **lambda@Edge** :customize req+some processing ) --> origin
  - **IAM:cognito** >> Lambda
  - **other services's event** >> eventBridge(**`EventRule`**/**`cron scheduler`**)  >> Lambda :point_left:
    - codePipeline's in-built event, say e1 > eventBridge(bus) > EventRule-1 (capture e1) > lambda-1 (process)
    - ...
    - generic pattern :)
- ![img_1.png](../99_img/compute/lambda/img_1.png)

---
## C. Architecture example
- ![img_3.png](../99_img/compute/lambda/img_3.png)
- ![img_2.png](../99_img/compute/lambda/img_2.png)
- ![img_6.png](../99_img/compute/lambda/img_6.png)
  