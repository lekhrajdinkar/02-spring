package com.lekhraj.java.spring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"kafka-topic-1", "kafka-topic-2"}, groupId = "kafka-generic-consumer-group")
    public void consume(String message) {
        try {
            if (message.contains("customerId")) {
                // Process Customer
                Customer customer = objectMapper.readValue(message, Customer.class);
                System.out.println("Received Customer: " + customer);
            }

            else if (message.contains("id")) {
                // Process Student
                Student student = objectMapper.readValue(message, Student.class);
                System.out.println("Received Student: " + student);
            }

            else {
                System.out.println("Unknown message: " + message);
            }
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
