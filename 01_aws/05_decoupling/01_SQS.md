# SQS 
- oldest & Fully managed  
- auto-scale
- unlimited throughput,  
- low latency (< 10ms), 
- max msg size : **256KB**
  - use extended-SQS, backed by s3.
  
## A De-couple Models in AWS
  - `queue` :  **SQS** 
    - coupled app (sync) 
    - de-couple app (Async)
  - `pub/sub` : SNS
  - `real time data-stream` : kinese Firehose
---
## B Types
### 1 Standard 
- multiple p1,p2,p3, ...  ---> [queue] ---> multiple consumers in parallel (C1,C2,C3, lambda-Consumer, ... )
- **at least once delivery**  
  - multiple consumer can receive same message.
  - consumer handle duplicate message, has to delete message.
  - idempotent consumer, if needed.
- **best effect ordering**
- **retention**: 
  - max     : `14 days` 
  - default :  `4 days`
- **visibility timeout** :o:
  - 0-12 hr
  - ![img_4.png](../99_img/decouple/sqs/img_4.png)
- **Long polling**  :o:
  - Consumer can optionally “wait” for messages to arrive, if there are none in the queue 
  - set `message receive wait time` : (1-20 sec)
  - pattern : poll-1 API -- wait 10 sec -- poll-1 API -- wait 10 sec ...
  - long poll preferred : more gap in poll api calls, but increase **latency**
  
### 2 FIFO
- name : has suffix `.fifo`
- keep single consumer
  - if having multiple consumer, then use group messages: `msgId + groupingId`
  - group-1 ( msg1, msg-2, ...) --> consumer-1
  - group-2 ( msg1, msg-2, ...) --> consumer-2
- **ordered** + **no duplicate consume**. :point_left:
- but, Limited **throughput**: 
  - `300 msg/s` without batch
  - `3000 msg/s` with batch

---
## C Security 
### general
- attach iam:sqs-policy.
- **In-flight encryption** 
  - `HTTPS` API (SSL/TLS)
- **At-rest encryption** 
  - KMS keys (`sse-sqs`, `sse-kms`, `sse-c`)
  - Client-side encryption :  if the client wants to perform encryption/decryption itself.
- `SQS bucket policy` : eg: cross queue access, allow other service, etc

---
## D price (cheap)
- **number of requests** 
  - Standard Queue : $0.40 / million requests.
  - FIFO Queue : $0.50 /  million requests.
- **data transfer**
  - inbound data is free
  - outbound traffic paid. ?
- Long Polling: No extra cost for long polling.

---
## E handson
```
- create queue : queue-1
- Type : standard ** + FIFO
- configuration:
  - `visibility timeout` : 30
  - `delivery delay`
  - `receive message weight time`
-  encrytion : sse-sqs
- policies:
    - SQS access policy : json
    - Redrive allow policy : pending
- Dead-letter queue
- tags

// READY
- send : hellow world
- receive : poll messages + delete
- purge : delete all message.
```
- ![img_2.png](../99_img/decouple/sqs/img_2.png)

---
## F use-case / arch eg
1. `SQS:queue:logs` >> CW >> metric >> alarm --> ASG [ ... multiple consumers ec2-i... ]
  - ![img.png](../99_img/decouple/sqs/img.png)
2. ASG [ FE-1, FE-2, ... ] ---> stage all request in Queue --- > ASG [ BE-1, BE-2, ...]
  - ![img_1.png](../99_img/decouple/sqs/img_1.png)
3. Overloaded DB request:
- ASG [ FE-1,...] --> Queue-1(Stage client request) --> ASG [BE-1,...] --> store to DB, OVERR-LOADED --> `lose some insert`
- ASG [ FE-1,...] --> Queue-1(`Stage client request`) --> ASG [BE-1,...] --> Queue-2(`stage-DB-request`) -->  ASG [BE-repo-1,...]
- ![img_3.png](../99_img/decouple/sqs/img_3.png)
- ![img_5.png](../99_img/decouple/sqs/img_5.png)

