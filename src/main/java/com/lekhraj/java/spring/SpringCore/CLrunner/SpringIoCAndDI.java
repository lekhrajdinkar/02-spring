package com.lekhraj.java.spring.SpringCore.CLrunner;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import com.lekhraj.java.spring.SpringCore.configuration.IoCcontract_1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // default
public class SpringIoCAndDI implements CommandLineRunner {
    static Logger log = LoggerFactory.getLogger(SpringIoCAndDI.class);
    //========================================
    // Step-1 :: Load bean with default contructor
    // Step-2 :: Resolve dependencies - Manual or Auto.
    // or
    // Step-1/2 :: Load bean with argument-contructor, which injects while creation
    // This is preferred, it keeps object Immutable.
    //
    // 1. DI(Manual) - Injection by Construtor
    // 2. DI(Manual) - Injection by Setter
    // 3. DI(Auto) - Type >> if 2+ >> @primary >> @Qualifier("beanName")
    //========================================
    @Autowired(required = true) // 3.1 DI - Auto (feild injection uses reflection)
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

    public void loadIaC(Class configClass){
        log.info("\n\n======= Loading Another Container  with IoCcontract_1 config only =======");
        ApplicationContext context = new AnnotationConfigApplicationContext(configClass);

        Item itemByName = (Item) context.getBean("item1");
        log.info(String.valueOf(itemByName.hashCode()));

        Item itemByType = (Item) context.getBean(Item.class); //NoUniqueBeanDefinitionException - use @primary
        log.info(String.valueOf(itemByType.hashCode()));
    }
    // ======= Runner ========
    @Override
    public void run(String... args) throws Exception
    {
        log.info(String.valueOf(store));
        log.info(String.valueOf(store.getItem11().hashCode()));

        loadIaC(IoCcontract_1.class); //  <<<<<<< comment/uncomment
    }
}

