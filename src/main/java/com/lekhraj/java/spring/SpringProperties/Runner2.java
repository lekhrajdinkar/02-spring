package com.lekhraj.java.spring.SpringProperties;

import com.lekhraj.java.spring.SpringProperties.bean.DatabasePropertiesMap;
import com.lekhraj.java.spring.SpringProperties.bean.RabbitPropertiesMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component("runner_2")
public class Runner2 implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(Runner2.class);

    @Autowired
    DatabasePropertiesMap dbmap;
    @Autowired
    RabbitPropertiesMap rabbitMap;
    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\n============= runner_2 (Spring properties) ============== START");

        log.info("\n --- DATABASE CONFIG MAP ---\n{}, \n --- RABBIT CONFIG MAP ---\n{}",dbmap, rabbitMap );

        System.out.println("\n============= runner_2 (Spring properties) ============== END\n\n");

    }
}
