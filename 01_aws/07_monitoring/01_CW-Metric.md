# Cloudwatch
## A. Cloudwatch : `Metric` :books:
- `namespace-1` : 
  - metric-1 : attribute/dimension-1, ... upto attribute-30. eg; instanceId,EnvName,etc
  - metric-2 : attribute/dimension-1, ...
  - ...
- `metric` are **variable** for monitoring
- **metric resolution** : how offer metric get run/refresh
  - `default` : 5 min.
  - `standard` : 1 min
  - `high` : 1/5/10/30 sec
  
### 1. Inbuilt metric
- metric enabled by default.
- can disable them.
- eg - for ec2:
  - `cpu utilization` 
  - `network call`
  - ...
  - NOTE: **ASG** uses these metric for auto-scaling.
    - change metric resolution to standard/high for better response :point_left:
  
### 2. Custom metric 
- can DefineAndSend **custom metric** on EC2 or any other service.
  - `RAM/memory utilization`
  - `disk utilization`
  - `number of logged User`
  - ...
- **PutMetricData** API
```
aws cloudwatch put-metric-data \
    --namespace <namespace-1> \
    --metric-name <metric_name> \
    --value <value> \
    [--dimensions <name=value,...>] \
    [--unit <unit>] \
    [--timestamp <timestamp>]

```
```
aws cloudwatch put-metric-data \
    --namespace MyApp/Performance \
    --metric-data file://metrics.json

# metrics.json    
[
  {
    "MetricName": "CPUUsage",
    "Value": 75.5,
    "Unit": "Percent",
    "Dimensions": [
      { "Name": "InstanceId", "Value": "i-12345678" }
    ]
  },
  {
    "MetricName": "DiskUsage",
    "Value": 60.0,
    "Unit": "Percent"
  }
]
```
### 3 dashboard
- **aws dashboard**
  - check on AWS console
  - check by region for ec2 or other service
  - check by namespace
  - ![img_1.png](img_1.png)
  
- **external dashboard**
  - create custom metric
  - deliver the stream of metric data, using `KDF` to:
    - 3rd party : `datadog, splunk, dynatrace`
    - then use 3rd party dashboard