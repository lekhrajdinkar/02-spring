package com.lekhraj.java.spring.database.repo;

import com.lekhraj.java.spring.database.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(transactionManager = "transactionManager_for_postgres")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
