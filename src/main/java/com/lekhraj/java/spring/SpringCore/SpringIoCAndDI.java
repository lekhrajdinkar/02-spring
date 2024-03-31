package com.lekhraj.java.spring.SpringCore;

import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringIoCAndDI implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(SpringIoCAndDI.class);

    // 1. DI - Injection by Construtor
    // 2. DI - Injection by Setter

    // 3.1 DI - Auto (feild injection uses reflection)
    @Autowired(required = true)
    Store store;

    SpringIoCAndDI(){
        log.info("Main :: default construtor");
    }

    //@Autowired(required = true) // 3.2 DI - Auto
    SpringIoCAndDI(Store store){
        log.info("SpringIoCAndDI :: construtor(Store) :: store:{}", store);
        this.store = store;
        this.store.setStoreName("target Super market");
    }

    //@Autowired(required = true)  // 3.3 DI - Auto
    public void setStore(Store store){
        log.info("SpringIoCAndDI :: setStore :: store:{}", store);
        this.store = store;
        this.store.setStoreName("target Super market");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(String.valueOf(store));
        log.info(String.valueOf(store.getItem1_again().hashCode()));
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
