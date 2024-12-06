# A. `Design pattern`
## Separation of Concerns
- Keep frontend, backend, and database independent.

## Service-Oriented Architecture (SOA)
- Microservices communicating over:
  - REST 
  - gRPC
  - messaging systems like RabbitMQ / Kafka.
  
## API-first Development:
- Design APIs before implementation to enable parallel development.

## Event-Driven Architecture
- Use `event streams` for real-time updates 
- Kafka, 
- AWS::SQS + AWS::EventBridge
- RMQ::StreamingQueue

## Serverless Architecture (auto-scale)
- AWS :: Lambda
  - for scalable and cost-effective solutions.
- Other AWS serverless offering 
  - fargate (ECS/EKS)
  - sqs,s3
  - AWS DB :: DynamoDB,Aurora

## Twelve-Factor App :point_here:
- Guidelines for building `scalable` and `maintainable` applications.
- -[02_twelve_factor_app.md](02_twelve_factor_app.md)

---
# B. Infrastructure `components`:
## Front-end
- React, Angular, Vue.js
- SPA, PWA

## Backend 
- SpringBoot, Express.js, Django/fastApi
- RESTful APIs, GraphQL APIs.
- **API Gateway**:
  - Routing, authentication, rate-limiting
  - ingress server in K8s
- **Microservices**
  - via containers (Docker) or orchestration (Kubernetes).
- Load Balancers
  - AWS Elastic Load Balancer, 
  - NGINX
- Cloud services 
  - AWS: lambda, s3, sqs
---
##  Database
- **Relational Databases**: 
  - PostgresSQL / Aurora
  - MySQL
- **NoSQL Databases**
  - MongoDB, 
  - Cassandra, 
  - AWS:DynamoDB
- Horizontal scaling
- distributed architecture
- **Caching**: 
  - Redis
  - Memcached

## CI/CD pipeline
- GitHub Actions
- Harness
- containerization: docker/k8s

## Observability and Monitoring (log,metric,traces)
- `OpenTelemetry`
- `micrometer`
- Prometheus, grafana, AWS:CloudWatch
- Application health 
- distributed tracing
  - AWS:CloudWatch>x-rays

## Authentication and Authorization
- [01_web_Security](../01_web_Security)
- SAML + SSO
- LDAP
- Okta
  - OAuth 2.0,
  - OpenID Connect

---
# C Design Aspect
## scalability
## reliability
## flexibility
## maintainability
## Observability