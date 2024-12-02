package com.lekhraj.java.spring.database;


import com.lekhraj.java.spring.database.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//@Component
public class DataInitializer_Runner
{
    @PersistenceContext(unitName = "entityManagerFactory_for_postgres")
    private EntityManager entityManagerPostgres;

    @Bean
    public CommandLineRunner insertData() {
        return args -> {
            // Insert Customers
            Customer john = new Customer();
            john.setName("John Doe");
            john.setEmail("john.doe@example.com");
            john.setAddress("123 Elm Street");

            Customer jane = new Customer();
            jane.setName("Jane Smith");
            jane.setEmail("jane.smith@example.com");
            jane.setAddress("456 Oak Avenue");

            // Insert Products
            Product laptop = new Product();
            laptop.setProductName("Laptop");
            laptop.setPrice(BigDecimal.valueOf(1200.00));

            Product smartphone = new Product();
            smartphone.setProductName("Smartphone");
            smartphone.setPrice(BigDecimal.valueOf(800.00));

            Product headphones = new Product();
            headphones.setProductName("Headphones");
            headphones.setPrice(BigDecimal.valueOf(150.00));

            // Insert Orders
            Order johnOrder = new Order();
            johnOrder.setCustomer(john);

            Order janeOrder = new Order();
            janeOrder.setCustomer(jane);

            // Insert Order Details
            OrderDetail johnOrderDetail1 = new OrderDetail();
            johnOrderDetail1.setOrder(johnOrder);
            johnOrderDetail1.setProductName("Laptop");
            johnOrderDetail1.setQuantity(1);
            johnOrderDetail1.setPrice(BigDecimal.valueOf(1200.00));

            OrderDetail johnOrderDetail2 = new OrderDetail();
            johnOrderDetail2.setOrder(johnOrder);
            johnOrderDetail2.setProductName("Headphones");
            johnOrderDetail2.setQuantity(2);
            johnOrderDetail2.setPrice(BigDecimal.valueOf(300.00));

            OrderDetail janeOrderDetail = new OrderDetail();
            janeOrderDetail.setOrder(janeOrder);
            janeOrderDetail.setProductName("Smartphone");
            janeOrderDetail.setQuantity(1);
            janeOrderDetail.setPrice(BigDecimal.valueOf(800.00));


            // Insert Carts
            Cart johnCart = new Cart();
            johnCart.setCustomer(john);

            Map<Product, Integer> johnCartItems = new HashMap<>();
            johnCartItems.put(laptop, 1);
            johnCartItems.put(headphones, 1);

            johnCart.setProductQuantities(johnCartItems);

            Cart janeCart = new Cart();
            janeCart.setCustomer(jane);

            Map<Product, Integer> janeCartItems = new HashMap<>();
            janeCartItems.put(smartphone, 1);

            janeCart.setProductQuantities(janeCartItems);

            // Persist Data
            entityManagerPostgres.persist(john);
            entityManagerPostgres.persist(jane);
            entityManagerPostgres.persist(laptop);
            entityManagerPostgres.persist(smartphone);
            entityManagerPostgres.persist(headphones);
            entityManagerPostgres.persist(johnOrder);
            entityManagerPostgres.persist(janeOrder);
            entityManagerPostgres.persist(johnOrderDetail1);
            entityManagerPostgres.persist(johnOrderDetail2);
            entityManagerPostgres.persist(janeOrderDetail);
            entityManagerPostgres.persist(johnCart);
            entityManagerPostgres.persist(janeCart);

            System.out.println("Data inserted successfully!");
        };
    }
}