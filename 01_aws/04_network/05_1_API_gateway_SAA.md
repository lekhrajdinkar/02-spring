# API gateway (Serverless)
## A. Intro
- **REST API** ( protocol : `http/s` + `websocket`), with other benefit:
  - **caching**
  - API **versioning**
  - API **documentation** / specification ?
  - **interceptor** : transform req/resp
  - more:
    - **No infrastructure** to manage. :)
    - create **environment**  - dev,qa,prod
    - **throttling** - rate limiting
    - **security** - check below.
    - **import**
      - already have pre-define API
      - from Swagger/OpenAPI
---
## B. API gateway: `integration` 
- **Backend**
  - API-g >> **lambda** 
    - pure serverless
    - most common
  - API-g >> **Any HTTP backend**
    - API-g >> **on-prem-API**
    - API-g >> **ALB**
      - expose ALB public directly. happening in ccgg.
      - expose API-g >> ALB > tg [ecs/eks - `container` - ec2/fargate]
  - API-g >> **`Any` AWS service API call.**  
    - s3:*
    - sqs:getMessage
    - kds:* :point_left:
    - ...
  
---  
## C. `Endpoint type`
- by **Deployment model** 
### 1 `regional`
- deployed in single region eg: us-west-1
- **for users in one region**
- can have **regional with CloudFont** eg:
  - set-1: for us-west-1
  - UNION
  - Set-2 : create CF distribution-1 (CF) : whiteList - europe user + india user

### 2 `edge-optimized` (default)
- deployed/lives in one region, but configure to
  - route request through CF many edge location/s
  - backed by : cloudFront distribution
- **for global user**

### 3 `private`
- with private VPC
- API-g >> **VPC endPoint**

---
## B. Security
### Authentication/Authorization
- for global user: 
  - integrate with **cognito**
- internal security
  - **IAM role**
  - custom Authorizer using **Lambda**
  
### SSL
- export certificate to **ACM**
- create **R53** entry (cname/alias)
  - integrated with ACM
  - keep certificate it `us-east-1` for edge-optimized endpoint.
  - certificate with backend server domain name.

---
## C. pricing

---
## Y. hands on
```
- create (4 Type) : api-gateway-1
    - HTTP API  >> lambda, http backend
    - REST API | REST API (Private,vpc-1) >> lambda, http backend, awsServices
    - Web-scoketAPI  >> lambda, http backend, + awsServices
    
  - choose - REST API
  - API details:  
        - create new **
        - import frpm OPen/AI swagger
        - clone API
  - deplomnet model : regional 
        - region : us-east-1
  - integration Type: lambda
        - choose method: lambda-fn-1
  - set timeout : default 30 s
  - check lambda > permission > "resource based policy statemnet" (policy/statemnet auto added)
        principle:api-gateway
        Action:lambda-Invoke
        resource:arnofLambda-1
        effect:allow
        condtion > sourceArn: api-gateway-1 
  - test from UI
  - Deploy API
        - will get invoke URL
```
![img_2.png](../99_img/moreSrv/api-gateway/img_2.png)

---
## Z. Architecture Example
- ![img.png](../99_img/moreSrv/api-gateway/img.png)
- ![img_1.png](../99_img/moreSrv/api-gateway/img_1.png)
