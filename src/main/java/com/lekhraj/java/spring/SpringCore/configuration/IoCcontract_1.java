package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_1;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/*
A. Container -
@Bean, @Component // each has name and type. //DefaultScope - Singleton(early-loaded)

B. DI
- manual0 - Setter + Constructor
- Auto ( @Autowited - apply at 3 places) - Automatically resolves >> type >> found 2+ >> Qualifier(name)

 */
@Configuration
public class IoCcontract_1 {
    @Bean()
   // @Bean(Autowire=Autowire.BY_TYPE) // deprecated as of Spring 5.1.
    public static Store store1() // Notice : static
    {
        //A. --- Setter injection ---
        Store bean = new Store();
        //bean.setItem1(item1());
        //bean.setItem1(IoCcontract_2.item2());
        System.out.println("IoCcontract_1 : Bean Created, Type-Store, name-store1, hashcode-"+bean.hashCode() );

        //B. --- Constructor injection ---
        // bean = new Store(item1());
        return bean;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // default
    public static Item item1(){
        Item bean = new ItemImpl_1();
        System.out.println("IoCcontract_1 : Bean Created, type-Item, name-item1, hascode-"+bean.hashCode());
        return  bean;
    }

    @Bean
    public static Item item1_again(){
        Item bean = new ItemImpl_1();
        System.out.println("IoCcontract_1 : Bean Created, type-Item, name-item1_again, hascode-"+bean.hashCode());
        return  bean;
    }
}
