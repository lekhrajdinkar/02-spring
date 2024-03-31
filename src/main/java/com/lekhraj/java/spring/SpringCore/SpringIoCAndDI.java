package com.lekhraj.java.spring.SpringCore;

import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class SpringIoCAndDI implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(SpringIoCAndDI.class);

    @Autowired Store store; // 3. Injection by reflection

    SpringIoCAndDI(){
        log.info("Main :: default construtor");
    }

    SpringIoCAndDI(Store store){
        log.info("Main :: construtor-1");
        this.store = store;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(String.valueOf(store));
    }
}
