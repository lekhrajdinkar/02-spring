# API-gateway - Stage :books:
## 1. Intro
![img.png](../99_img/dva/api-g/01/img.png)
- once created API-g, need to deploy it on **stages**
  - give stage a **name** -dev,qa,prod,whatever
  - stages has **deployment history** 
    - stage can be **rollback**.
    - so think of `deploymnet object in k8s`
  - has **stage variable**, for dynamic configuration for stages. eg:
    - lambda-arn
    - http-endpoint-uri
    - ...
    - note: passed to **context** object. => lambda.handler(event,`context`) :point_left:
    - format **${stageVariables.`var-1`}**

---    
## 2. example
```yaml
    - api-gateway-1:
      - integrated with lambda-1 arn ==> xxxx:${stageVariables.`lambda-alias`} 
        - created one stage variable
        - similarly create more stage variable.
      - set value of stageVariable "lambda-alias" in all stages, then
      - stages:
        - dev-1
          - set lambda-alias: dev
          - dev is point to $LATEST
        - qa-1
          - set lambda-alias: v10
```

---
## 3. **`cache` setting**
- cache request/response
- define at stage level
- provision **size** : `0.5 GB` to `237 GB`
- set cache object **TTL**
  - default: `5 min / 300 sec`
  - max: `1 hr / 3600 sec`
- optionally encrypt

### **cache Invalidation** :point_left:
- from console
- any client without/with iam:permission can do it
  - header: **cache-control:max-age = 0**
  - iam permission : **execute-api:InvalidateCache**

---  
## 4. canary deployment
![img.png](../99_img/dva/api-g/02/img.png)
- api-gateway-1
    - stage-1 (`95 %` traffic) : monitor-1
    - stage-2 ( `5 %` traffic) : monitor-2
- console >>  **canary tab**
    - set above configuration.

---
## 5. supports `OpenAPI`
- export:
  - ![img.png](../99_img/dva/api-g/01/img-openAPI.png)
  - this is API document which client can refer.
- generate SDK
  - for client to consume API
- [api-gateway-open-api-1.json](api-gateway-open-api-1.json)
  - Replace `{region}` with your AWS region
  - Replace `{lambda-arn}` with the ARN of the Lambda function handling the request.
  - check **schema**. :point_left:
  - import this into api-gateway
  - **schema validation**

---
## 4. usage plan and API keys

---
## 5. Monitor
- `CW:logs` : enable/disable
    - error only
    - error + info
    - full req + response log
- `CW:x-rays` : enable/disable
- `CW:metric` : enable/disable