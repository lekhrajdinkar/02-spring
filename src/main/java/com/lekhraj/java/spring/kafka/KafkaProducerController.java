package com.lekhraj.java.spring.kafka;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaProducerController {
    private final KafkaProducerService producerService;

    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/student")
    public String sendStudent(@RequestParam String topic, @RequestBody Object student) {
        producerService.sendStudent(topic, student);
        return "Student message sent to topic: " + topic;
    }

    @PostMapping("/customer")
    public String sendCustomer(@RequestParam String topic, @RequestBody Object customer) {
        producerService.sendCustomer(topic, customer);
        return "Customer message sent to topic: " + topic;
    }
}

/*

{"customerId": "C123", "customerName": "Bob", "email": "bob@example.com"}
{"id": "1", "name": "Alice", "age": 22}

 */

