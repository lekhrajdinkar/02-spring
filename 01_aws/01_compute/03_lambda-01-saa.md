# AWS Lambda 
## A. Lambda:Function
- ![img.png](../99_img/dva/l/01/img.png)
- lambda initially was `FaaS`. Now serverless : `provision code/function` 
- invoke:
  - by services(eg: s3 trigger lambda)
  - by SDK/CLI
    - `--invocation-type Event` for making async call from cli

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
- Attach IAM role with fine grain access to lambda
- **lambda policy** 
  - who can invoke the Lambda function and under what conditions 
  - eg: allow S3 bucket:
```
aws lambda add-permission \
    --function-name <function_name> \
    --action "lambda:InvokeFunction" \    <<<
    --principal s3.amazonaws.com \
    --source-arn arn:aws:s3:::<bucket_name> \
    --statement-id <unique_id>

aws s3api put-bucket-notification-configuration \
    --bucket <bucket_name> \
    --notification-configuration file://notification.json
    
# notification.json    
{
  "LambdaFunctionConfigurations": [
    {
      "LambdaFunctionArn": "arn:aws:lambda:region:account-id:function:function-name",
      "Events": ["s3:ObjectCreated:*"]
    }
  ]
}
```

### 5 programming things :book:
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
- **env var** : `4 KB`
- **build pkg size** :
  - `50 MB`  compressed
  - `250 MB` code+dependency
-  **Monitor** : 
  - log, metric, trace.
  - log-group : /aws/lambda/lambda-1/
  - each run creates new log stream.
- **Lambda Container Image** 
  - run docker image in lambda Function
  - `base image` : lambda runtime API

## B. integration with other services :green_circle: 
- **lambda trigger** patterns:  :point_left:
  - **Event source mapping** 
  - **Synchronous** 
  - **A-synchronous**
  - details: [03_lambda-dav-01.md](03_lambda-dva-02) for details

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
  
