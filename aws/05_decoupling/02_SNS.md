## SNS - PUB/SUB decouple Model
- ![img.png](../99_img/decouple/sns/img.png)
- 100k topic per account, can be extended.
- Like SQS, Standard + FIFO
- event producer --> topic(message +`attributes`) --> subscriber-1,2,3... `12 million`
  - all message will go to all subscribers
  - subscription- filter policy 
    - filter message by `message attribute`
    - ![img_6.png](../99_img/decouple/sns/img_6.png)

---
## List : Subscriber and publisher 
- ![img_1.png](../99_img/decouple/sns/img_1.png)
- ![img_2.png](../99_img/decouple/sns/img_2.png)

---
## SNS : Security
- Encryption
    - In-flight encryption using `HTTPS` API (SSL/TLS)
    - At-rest encryption using KMS keys (`sse-sqs`, `sse-kms`, `sse-c`)
    - `Client-side encryption` if the client wants to perform encryption/decryption itself
- `SNS bucket policy` : eg: cross queue access, allow other service, etc
- principle (attach `IAM policy`) --> access queue

---
## use case
- SQS + SNS fan out pattern
  - ![img_3.png](../99_img/decouple/sns/img_3.png)
  - ![img_4.png](../99_img/decouple/sns/img_4.png)
  - ![img_5.png](../99_img/decouple/sns/img_5.png)
