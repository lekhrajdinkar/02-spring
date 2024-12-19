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
    - zip and upload (template and code) to s3

- **sam deploy**  : deploy cf stack
  - use **CodeDeploy** to deploy Lambda functions
  
- **sam sync** --watch
  - ![img_1.png](../../99_img/dva/sam/01/img_1.png)

---
## C. Hands ON
### 1. Deploy lambda function with API-gateway
- added **env var** from template
- added **polices** from template
![img_2.png](../../99_img/dva/sam/01/img_2.png)
```yaml
AWSTemplateFormatVersion: '2010-09-09'
Transform: AWS::Serverless-2016-10-31
Description: A simple AWS SAM example

Resources:
  HelloWorldFunction:
    Type: AWS::Serverless::Function
    Properties:
      Handler: app.lambda_handler
      Runtime: python3.11
      CodeUri: lambda/
      
      # Environmnet var
      Environment:
        Variables:
          # TABLE_NAME: HelloWorldTable
          TABLE_NAME: !Ref HelloWorldTable
          REGION_NAME: !Ref AWS::Region
      
      # polices to perform CRUD on table    
      Policies:
        - DynamoDBCrudPolicy:
            TableName: !Ref HelloWorldTable
      
      # expose as API gateway      
      Events:
        HelloWorldApi:
          Type: AWS::Serverless::Api
          Properties:
            Path: /hello
            Method: get
```
---

### 2. add DynamoDB table
```yaml

Resources:  
  HelloWorldTable:
    Type: AWS::DynamoDB::Table
    Properties:
      TableName: HelloWorldTable
      PrimaryKey:
        Name: id
        Type: String
      ProvisionedThroughput:
        ReadCapacityUnits: 5
        WriteCapacityUnits: 5
```
![img.png](../../99_img/dva/sam/01/img_4.png)
---

