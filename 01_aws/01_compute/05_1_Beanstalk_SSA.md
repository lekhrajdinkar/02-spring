# Elastic Beanstalk 
## A. Common **Web** Architecture
![img.png](img.png)

## B. Intro
- Managed services, deals with:
  - **Managing infrastructure**
    - databases, `RDS`
    - load balancers, `ELB` + networking
    - scaling concerns, `ASG`
    - Monitoring
    - ...
    - note:create cloudFormation template bts for infra.
    
  - **Deploying Code**
    - into multiple env, 
    - compute: EC2/ECS/Lambda
    
- Basically from central place, dealing with all these services, being used commonly for web development. :point_left:
  - **developer focus on code**
  - rest all beanStalk will do `infra + deploymnet`

## C. pricing
- `Free` 
- but pay for underlying infra.

## D. Elastic Beanstalk : `Application`
### D.1 **configuration**
- **deployment Mode**: 
  - **single instance** (with/without using spot)
  - **High availability** (with/without using spot+od)
  - ![img_1.png](../99_img/compute/img_1.png)
  
- **Networking**
- **Database**
- **Monitoring**

### D.2 **Application version** 
- define **platform** - language and runtime
- upload code
  - code, v1
  - code, v2
  - ...

### D.3 **Environment/s** 
- create multiple `dev, prod, qa`
- it represents infra which are running our application version.
- Type/tier:
  - **web-server**  Environment
    - can send traffic to worker
  - **worker** Environment
    - for long-running task,jobs,scheduledJobs
  - ![img.png](../99_img/compute/img.png)
      
---
## Z. Hands on
```yaml
- Create Application
  - create environment : dev
    - choose type/tier: web | worker(job,long-running-task)
  - choose platform : language, runtime,etc 
  - code: upload it.
  - deployment mode:
    - single EC2 with ASG 
    - high availabilty - multiple EC2 with ASG
  - iam role for permission:
      - `AWSElasticBeanStalkWebTier`,
      - `AWSElasticBeanStalkWorkerTier`, 
      - `AWSElasticBeanStalkMultiContainerDocker`
      - ...
  - Skipped and using default
    - Networking
    - Database
    - Monitoring
  
# === DONE === 
  
- check cloudFormation stack (composer UI)

- get domain url and use it.
- upload new code, seamlessly.
- check other tab : CW, monitor, mmanged updates, alarms, health, etc
- Check `configuration` link on left
  - show all the configs for env
  - edit them

```
