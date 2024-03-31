package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_1;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoCcontract {
    @Bean
    public Store store1(){
        System.out.println("IoCcontract : Create Store1");
        return new Store(item1());
    }

    @Bean
    public Item item1(){
        System.out.println("IoCcontract : Create Item1");
        return new ItemImpl_1();
    }
}
