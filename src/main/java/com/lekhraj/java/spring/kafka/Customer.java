package com.lekhraj.java.spring.kafka;

import lombok.Data;

@Data
public class Customer {
    private String customerId;
    private String customerName;
    private String email;
}
