# Cloudwatch : `Alarm`  
- Trigger notifications for any **metric**
- **states**
  - `ok` 
  - `in-alarm `
  - `insufficient-data`
  - 
- **action** : 
  - stop
  - ec2 : reboot,recover 
  - asg
  - sns 
  - ...
- **composite alarm** 
  - alarm1 AND/OR alarm2
  
- alarm can be created on:
  - metric
  - logs
  
![img.png](../99_img/decouple/ct/img.png)

---
## D. demo
```
// 5 . create alarm
- launch ec2-i1 
- CW > create metric-1
  - add ec2-i1
  - type : cpu
  - every 5 min, 3 of 3
  - greater than 90%
  - state action:
    - ok > action:
    - in-alarrm > action : terminate ec2-i1
    - insufficient-data > action :
     >> manually update state from aws-cli
    
...

```


