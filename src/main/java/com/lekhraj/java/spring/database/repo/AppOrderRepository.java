package com.lekhraj.java.spring.database.repo;

import com.lekhraj.java.spring.database.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(transactionManager = "transactionManager_for_postgres")
public interface AppOrderRepository extends JpaRepository<AppOrder, Long> {
}
