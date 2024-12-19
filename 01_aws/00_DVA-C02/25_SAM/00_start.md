- https://chatgpt.com/c/6763704a-0a24-800d-b9a2-1866b9931f4d
- https://github.com/aws/aws-sam-cli-app-templates
---
# SAM (Serverless Application Model)
- serverless : Lambda, API Gateway, DynamoDB, step function

## A. Intro
![img.png](../../99_img/dva/sam/01/img.png)
- **Framework** for simplifying serverless application deployment
- AWS SAM templates (YAML/JSON).
  - defines **resources**
    - `AWS::Serverless::Function`
    - `AWS::Serverless::Api`
    - `AWS::Serverless::SimpleTable`
    
## B. sam cli
- **sam init**
- **same build**
- **sam package** : creates cloudformation stack
  - `--template-file` 
  - `--output-template-file` 
  - `--s3-bucket`
    - lambda code will be uploaded to s3

- **sam deploy**  : deploy cf stack
  - use **CodeDeploy** to deploy Lambda functions
  
- **sam sync** --watch
  - ![img_1.png](../../99_img/dva/sam/01/img_1.png)

---
## C. Hands ON
### 1. Deploy lambda function
![img_2.png](../../99_img/dva/sam/01/img_2.png)
