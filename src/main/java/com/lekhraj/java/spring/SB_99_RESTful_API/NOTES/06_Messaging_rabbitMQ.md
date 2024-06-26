# Messaging 
- Distribted system, micro-services, etc communicates async.
- `message broker` system - RMQ, Kafka, SQS, ActiveMQ, Google pub/sub
- `messaging protocols` :  
  - HTTP/HTTPS : RestApi messages
  - WebSockets: full-duplex communication channels over a single TCP connection.
  - `MQTT` (Message Queuing Telemetry Transport)
  - `AMQP` (Advanced Message Queuing Protocol) : `RabbitMQ`
  - `STOMP` (Simple Text Oriented Messaging Protocol)

---
## RabbitMQ
- https://www.rabbitmq.com/tutorials
- Producer  --> `Exchange`(Route) -->  Queue --> consumer
- Queue: large message buffer.
- Exchange Type:
  -  `Direct`: Routes messages to queues based on `message routing key`.
  -  `Topic`: Routes messages to queues based on `"wildcard matching" of the routing key`.
  -  `Fanout`: Routes messages to `all queues` bound to it.
  -  `Headers`: Uses `message headers` for routing.
- Queues:
  - `Classic`: traditional 
  - `Quorum`: more resilient and `highly available` (replicated across multiple nodes), data consistency.
  - `Streams`: kafka kind
    - designed for event streaming use cases.
    - more extensive retention policies
    - supports `multiple consumers with different offsets`.
    - Optimized for `high-throughput` and `low-latency` messaging.
  - more :
    - `Federated Queues`: link queues across different RabbitMQ nodes
    - `Lazy Queues` : large queues without consuming too much RAM. since it store om disk.
    - `Priority Queues`: Suitable when message prioritization is critical.

## RabbitMQ Set up
- Conn factory > conn > channel > Queue-Declare > publish/receive
- run server:
  - https://hub.docker.com/_/rabbitmq
  - docker run -d --hostname my-rabbit --name my-rabbit -p 5672:5672 -p 15672:15672  rabbitmq:3-management
  - console : guest/guest http://localhost:15672/
  - https://docs.spring.io/spring-boot/reference/messaging/amqp.html#messaging.amqp
  - env : `ssl_options.verify` : verify_none