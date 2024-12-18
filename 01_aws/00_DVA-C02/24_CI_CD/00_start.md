- [05_harness](../../../05_harness)
- [04_terraform](../../../04_terraform)
---
# A. Concept
- CI
- CD
- pipeline
- IAC

# B. CodePipeline
- attach iam role with correct permission.
- **stage/s**
  - stage:success
  - stage:`failed` --> eb:event --> notify
  - **step/s**
    - step:success
    - step:`failed` --> eb:event --> notify
- pipeline creates **artifact** in ` s3`, then passed to next stage.
- ![img.png](../../99_img/dva/ci_cd/01/img.png)
- ![img_1.png](../../99_img/dva/ci_cd/01/img_1.png)

---
## 0. codeCommit
- VCS
- use external git: **github**
- service terminate in july 2024 for new customer.

---
## 1. CodeBuild
- fully managed continuous integration (CI) service
- **build project**:
  - add `buildspec.yaml` (root dir)
    - env
    - phases
    - artifact for s3
    - cache :point_left:
  - Compile source code, run tests, produce software packages
  - Leverages Docker under the hood for reproducible builds :point_left:
    - build container
    - Use prepackaged Docker images
    
![img_2.png](../../99_img/dva/ci_cd/01/img_2.png)

---    
## 2. CodeDeploy
- deploy to container(ecs/eks),beanstalk infa, etc
- add `appsec.yaml`
- **CodeDeploy Agent** on the target instances
  - on ecs/ec2 already present
  - on-prem, install it.
- **deployment speed**:
  - ![img_5.png](../../99_img/dva/ci_cd/01/img_5.png)
```
• AllAtOnce: most downtime
• HalfAtATime: reduced capacity by 50%
• OneAtATime: slowest, lowest availability impact
• Custom: define your %
```

### blue-green deployment
- ![img_3.png](../../99_img/dva/ci_cd/01/img_3.png)
- use codeDeploy strategies for **traffic shifting** :point_left:
  - **Linear**: grow traffic every N minutes until 100%
  - **Canary**: try X percent then 100%
  - **AllAtOnce**: immediate
- ![img_4.png](../../99_img/dva/ci_cd/01/img_4.png)

---
## 3. AWS CodeStar 
– manage software development activities in one place

---
## 4. AWS CodeArtifact 
– store, publish, and share software packages

---
## 5. AWS CodeGuru 
– automated code **reviews** using Machine Learning