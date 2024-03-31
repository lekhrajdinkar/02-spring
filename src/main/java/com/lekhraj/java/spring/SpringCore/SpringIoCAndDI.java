package com.lekhraj.java.spring.SpringCore;

import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringIoCAndDI implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(SpringIoCAndDI.class);

    // 1. DI - Injection by Construtor
    // 2. DI - Injection by Setter
    @Autowired(required = true)
    Store store; // 3. DI - Injection by Feild (uses reflection)

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

/*
@Autowired types::
=================
1. no:
------
the default value – this means no autowiring is used for the bean
and we have to explicitly name the dependencies.

2. byName:
---------
autowiring is done based on the name of the property,
therefore Spring will look for a bean with the same name
as the property that needs to be set.

3. byType:
---------
similar to the byName autowiring, only based on the type of the property.
This means Spring will look for a bean with the same type of the property to set.
If there’s more than one bean of that type, the framework throws an exception.

4. constructor: <<<<
---------------
autowiring is done based on constructor arguments, meaning Spring will
look for beans with the same type as the constructor arguments.
 */
