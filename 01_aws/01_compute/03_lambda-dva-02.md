# Lambda Invocations
## A. `Synchronous`
- below services make sync/blocking call:
  - ALB, Amazon API Gateway, CloudFront (Lambda@Edge)
  - Amazon S3-Batch
  - Amazon Cognito
  - AWS Step Functions
  - Amazon Lex
  - Amazon Alexa
  - Amazon Kinesis Data Firehose
  
---  
###  A.1 **ALB**
- ![img_1.png](../99_img/dva/l/01/img_1.png)
- **Flow**
  - http/s request  comes to ELB
  - elb `converts` **http request into json** object and pass to **event** arg
  - call lambda-1 > handler-1(**event**, context)
  - lambda sends json object as response
  - ELB `converts` **lambda-response into http-response**
  ```
  {
  "statusCode": 200,
  "statusDescription": "200 OK",
  "isBase64Encoded": false,
  "headers": {
    "Content-Type": "text/html",
    "Set-Cookie": "cookie1=value1; Path=/; HttpOnly",
      "Custom-Header": "value"
    },
  "body": "<html><body>Hello from Lambda!</body></html>"
  }
  ```
- **conversions**:
  - ![img_2.png](../99_img/dva/l/01/img_2.png)
  - ![img_3.png](../99_img/dva/l/01/img_3.png)
- **ALB Multi-Header Values** : enable/disable this feature. :point_left:
  - notice queryparams,headers `array`
  - ![img_4.png](../99_img/dva/l/01/img_4.png)

---
###  A.2 **gateway**
- soon

---
## B `A-synchronous`
- below services make a-sync/non-blocking call:
  - s3:evnetNotification
  - SQS, SNS, SES(email)
  - CW:log-events||subscription-filter
  - more (not in scope of exam)
    - AWS codeCommit + codePiprline + cloudformation
    - AWS config
    - AWs IoT

---
### B.1 S3:event notification :green_circle:
- ![img_2.png](../99_img/dva/l/02/img_2.png)
- ![img_3.png](../99_img/dva/l/02/img_3.png)
- create s3 bucket > properties tab >> create s3 notification
  - set **prefix** +  s3:objectCreate event
  - set destination : lambda-1 (lambda-policy-1: allow s3)

---
### B.2 SQS :green_circle:
- ![img.png](../99_img/dva/l/02/img.png)
- here, call lambda asyn and wait in parallel. does not block.
- lambda-1 >> configuration tab >> **asynchronous invocation** section. :point_left:
  - add `DLQ-1`
  - set `retry attempt` = 0,1,2
  - also, update lambda-role-1: + sqsSend permission
- if exception thrown from lambda(consumer), then lambda will retry
  - keep lambda code **Idempotent** 
  - after **reties** goes to DLQ-1

---
### B.3 Eventbridge (generic pattern) :green_circle:
- **trigger**:
  - EventRule
  - schedular
  - ![img_1.png](../99_img/dva/l/02/img_1.png)

---
## C. `Event Source Mapping`
-  Lambda is triggered **`synchronously` with `batch`** :point_left:
- by **polling data** from below **poll-based services** :point_left:
  -  `SQS`
  -  `KDS`
  - `DynamoDB Streams`
- concept:
  - ![img.png](img.png)

### C.1 SQS : event-source-mapping :yellow_circle:

### C.2 KDS : event-source-mapping :yellow_circle:

### C.3 DynamoDB : event-source-mapping :yellow_circle:

