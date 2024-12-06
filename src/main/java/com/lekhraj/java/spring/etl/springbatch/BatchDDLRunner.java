package com.lekhraj.java.spring.etl.springbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

    @Configuration
    public class BatchDDLRunner {

        @Autowired @Qualifier("dataSource_for_postgres2")
        private DataSource dataSource;

        @Bean
        public CommandLineRunner initializeBatchSchema() {
            return args -> {
                try (Connection connection = dataSource.getConnection();
                     Statement statement = connection.createStatement())
                {

                    String ddlScript = """
                    DROP TABLE app_user;
                    CREATE TABLE "app_user" (
                                    id SERIAL PRIMARY KEY,
                                    firstname VARCHAR(100),
                                    lastname VARCHAR(100),
                                    email VARCHAR(100)
                                );
                                
                    -- Autogenerated: do not edit this file
                                DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION_CONTEXT;
                                DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_CONTEXT;
                                DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION;
                                DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_PARAMS;
                                DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION;
                                DROP TABLE  IF EXISTS BATCH_JOB_INSTANCE;
                            
                                DROP SEQUENCE  IF EXISTS BATCH_STEP_EXECUTION_SEQ;
                                DROP SEQUENCE  IF EXISTS BATCH_JOB_EXECUTION_SEQ;
                                DROP SEQUENCE  IF EXISTS BATCH_JOB_SEQ;
                                
                    -- Autogenerated: do not edit this file
                            
                                                                                                                  CREATE TABLE BATCH_JOB_INSTANCE  (
                                                                                                                  	JOB_INSTANCE_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                                                                                  	VERSION BIGINT ,
                                                                                                                  	JOB_NAME VARCHAR(100) NOT NULL,
                                                                                                                  	JOB_KEY VARCHAR(32) NOT NULL,
                                                                                                                  	constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE TABLE BATCH_JOB_EXECUTION  (
                                                                                                                  	JOB_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                                                                                  	VERSION BIGINT  ,
                                                                                                                  	JOB_INSTANCE_ID BIGINT NOT NULL,
                                                                                                                  	CREATE_TIME TIMESTAMP NOT NULL,
                                                                                                                  	START_TIME TIMESTAMP DEFAULT NULL ,
                                                                                                                  	END_TIME TIMESTAMP DEFAULT NULL ,
                                                                                                                  	STATUS VARCHAR(10) ,
                                                                                                                  	EXIT_CODE VARCHAR(2500) ,
                                                                                                                  	EXIT_MESSAGE VARCHAR(2500) ,
                                                                                                                  	LAST_UPDATED TIMESTAMP,
                                                                                                                  	constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID)
                                                                                                                  	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
                                                                                                                  	JOB_EXECUTION_ID BIGINT NOT NULL ,
                                                                                                                  	PARAMETER_NAME VARCHAR(100) NOT NULL ,
                                                                                                                  	PARAMETER_TYPE VARCHAR(100) NOT NULL ,
                                                                                                                  	PARAMETER_VALUE VARCHAR(2500) ,
                                                                                                                  	IDENTIFYING CHAR(1) NOT NULL ,
                                                                                                                  	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
                                                                                                                  	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE TABLE BATCH_STEP_EXECUTION  (
                                                                                                                  	STEP_EXECUTION_ID BIGINT  NOT NULL PRIMARY KEY ,
                                                                                                                  	VERSION BIGINT NOT NULL,
                                                                                                                  	STEP_NAME VARCHAR(100) NOT NULL,
                                                                                                                  	JOB_EXECUTION_ID BIGINT NOT NULL,
                                                                                                                  	CREATE_TIME TIMESTAMP NOT NULL,
                                                                                                                  	START_TIME TIMESTAMP DEFAULT NULL ,
                                                                                                                  	END_TIME TIMESTAMP DEFAULT NULL ,
                                                                                                                  	STATUS VARCHAR(10) ,
                                                                                                                  	COMMIT_COUNT BIGINT ,
                                                                                                                  	READ_COUNT BIGINT ,
                                                                                                                  	FILTER_COUNT BIGINT ,
                                                                                                                  	WRITE_COUNT BIGINT ,
                                                                                                                  	READ_SKIP_COUNT BIGINT ,
                                                                                                                  	WRITE_SKIP_COUNT BIGINT ,
                                                                                                                  	PROCESS_SKIP_COUNT BIGINT ,
                                                                                                                  	ROLLBACK_COUNT BIGINT ,
                                                                                                                  	EXIT_CODE VARCHAR(2500) ,
                                                                                                                  	EXIT_MESSAGE VARCHAR(2500) ,
                                                                                                                  	LAST_UPDATED TIMESTAMP,
                                                                                                                  	constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID)
                                                                                                                  	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
                                                                                                                  	STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                                                                                                  	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                                                                                                  	SERIALIZED_CONTEXT TEXT ,
                                                                                                                  	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
                                                                                                                  	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
                                                                                                                  	JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY,
                                                                                                                  	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
                                                                                                                  	SERIALIZED_CONTEXT TEXT ,
                                                                                                                  	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
                                                                                                                  	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
                                                                                                                  ) ;
                            
                                                                                                                  CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
                                                                                                                  CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
                                                                                                                  CREATE SEQUENCE BATCH_JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
                """;

                    statement.executeUpdate(ddlScript);
                    System.out.println("Spring Batch schema initialized successfully!");

                } catch (Exception e) {
                    System.err.println("Error initializing Spring Batch schema: " + e.getMessage());
                    e.printStackTrace();
                }
            };
        }
    }

