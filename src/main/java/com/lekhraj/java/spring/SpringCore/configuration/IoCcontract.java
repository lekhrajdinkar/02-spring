package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_1;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoCcontract {
    @Bean()
   // @Bean(Autowire=Autowire.BY_TYPE) // deprecated as of Spring 5.1.
    public static Store store1(){
        System.out.println("IoCcontract : Create Store1");
        //A. --- Constructor injection ---
        //return new Store(item1());

        //B. --- Setter injection ---
        Store bean = new Store();
        bean.setStoreName("target Super market");
        bean.setItem1(item1()); //setter
        bean.setItem1(IoCcontract_2.item2()); //setter
        return bean;
    }

    @Bean
    public static Item item1(){
        System.out.println("IoCcontract : Create Item1");
        return new ItemImpl_1();
    }
}
