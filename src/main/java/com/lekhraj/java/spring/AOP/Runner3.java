package com.lekhraj.java.spring.AOP;

import com.lekhraj.java.spring.AOP.annotation.MyAopMetric;
import com.lekhraj.java.spring.SpringCore.Runner1;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class Runner3 implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(Runner1.class);
    @Override @MyAopMetric
    public void run(String... args) throws Exception {
        task1();
        task2();
    }

    @SneakyThrows  //@MyAopMetric
    public void task1(){
        Thread.sleep(2000);
    }

    @SneakyThrows  //@MyAopMetric
    public void task2(){
        Thread.sleep(3000);
    }
}
