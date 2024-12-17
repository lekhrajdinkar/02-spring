# API gateway (Serverless)
## A. Intro
- **REST API** ( protocol : `http` + `websocket`), with other benefit:
  - **caching**
  - API **versioning**
  - API **documentation** / specification ?
  - **interceptor** : transform req/resp
  - more:
    - **No infrastructure** to manage. :)
    - create **environment**  - dev,qa,prod
    - **throttling** - rate limiting
    - **security** - so many things. check below.
    - **import**
      - already have pre-define API
      - from Swagger/OpenAPI
---
## B. API gateway: **Backend**
- API-g >> **VPC endPoint**
- API-g >> **lambda** 
  - pure serverless
  - most common
- API-g >> **ALB**
  - expose ALB public directly. happening in ccgg.
  - expose API-g >> ALB > tg [ecs/eks - `container` - ec2/fargate]
- API-g >> **ALL AWS service API call.**  
  - s3:*
  - sqs:getMessage
  - ...
- API-g >> **on-prem-API**

  
---  
## C. Deployment model
### `edge-optimized` (default)
- deployed in many edge location/s
- backed by : cloudFront distribution.
- for global user
### `regional`
- deployed in single region eg: us-west-1
### `private`
- in private VPC
- Also, VPC endpoint(interface) --> API gateway --> XXXXX
### `regional with CloudFont`
- set-1: for us-west-1
- UNION
- Set-2 : create CF distribution-1 (CF) : whiteList - europe user + india user
---

## B. Security :point_left:
- IAM role
- OIDC and OAuth2
- integrate with cognito
- https/TCL with ACM 
  - keep certificate it `us-east-1` for global deployment model.



---
## Y. hands on
```
- create (4) : api-gateway-1
    - HTTP API , REST API ** , REST API (Private),  Web-scoketAPI, 
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
