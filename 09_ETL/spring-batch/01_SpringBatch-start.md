# Spring-batch (5.x.x)
- project : [springbatch](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Flekhraj%2Fjava%2Fspring%2Fspringbatch)
- `Spring Framework 6.x` | `Spring Boot 3.x` | `Java 17`
- powerful framework for batch processing in Java.
- designed to handle **large volumes of data** by:
    - dividing it into smaller chunks,
    - providing capabilities like transaction management,
    - parallel chunk processing

## key components
- **Job**     : Represents the batch process.
- **Step**    : A stage in the job (e.g., reading, processing, writing).
- **ItemReader** : Reads data from a source.
- **ItemProcessor** : Processes data (optional).
- **ItemWriter** : Writes processed data to a destination.
- **JobRepository** - 
  - explicitly handles persistence of batch metadata, ensuring better control and compatibility with the latest versions.

---
## Key Features
- resilient step and job restarts.
- support for high-concurrency job execution
- **Observability**:
    - Out-of-the-box support for `distributed tracing` and `metrics` using **Micrometer**.
    - Compatible with tools like **OpenTelemetry** for tracing batch jobs.
    - Configure tracing and metrics to monitor your batch jobs in a `distributed environment`
  ```
  <dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
   </dependency>
  
   <dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-otlp</artifactId>
   </dependency>

  ```
  - https://github.com/spring-projects/spring-batch/blob/main/spring-batch-core/src/main/resources/org/springframework/batch/core/schema-postgresql.sql