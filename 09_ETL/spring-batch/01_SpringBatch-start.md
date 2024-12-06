# Spring-batch (5.x.x)
- project : [springbatch](../../src/main/java/com/lekhraj/java/spring/etl/springbatch)
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
```
@EnableBatchProcessing(
        dataSourceRef = "dataSource_for_postgres2",
        transactionManagerRef = "transactionManager_for_postgres2"
)

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-batch</artifactId>
		</dependency>

@EnableAsync 
tells Spring to look for methods annotated with @Async and execute them asynchronously.
		
```
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
- RUN DDL:
  - https://github.com/spring-projects/spring-batch/blob/main/spring-batch-core/src/main/resources/org/springframework/batch/core/schema-postgresql.sql
  - [BatchDDLRunner.java](../../src/main/java/com/lekhraj/java/spring/etl/springbatch/BatchDDLRunner.java)
  - table (6)
    - batch_job_execution: Stores metadata about job executions, such as job parameters, status, and the time it was started and finished.
    - batch_job_execution_context: Stores execution context data (such as intermediate results or application-specific data) for each job execution. It is associated with batch_job_execution via a foreign key.
    - batch_job_execution_params: Stores the parameters passed to the job during its execution, usually stored as key-value pairs.
    - batch_job_instance: Stores metadata about the job instance. This tracks the distinct logical execution of a job. For example, if you run the same job multiple times with different parameters, each run would have a separate job instance.
    - batch_step_execution: Stores metadata about individual step executions within a job, such as status, start time, and end time for each step.
    - batch_step_execution_context: Similar to batch_job_execution_context, but this table stores the execution context data for each step of the job.

  