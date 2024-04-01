package com.lekhraj.java.spring.AOP;

import com.lekhraj.java.spring.SpringCore.Runner1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner3 implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(Runner1.class);
    @Override
    public void run(String... args) throws Exception {
        task1();
        task2();
    }

    public void task1(){
        log.info(" ### task1 ###");
    }

    public void task2(){
        log.info(" ### task2 ###");
    }
}
