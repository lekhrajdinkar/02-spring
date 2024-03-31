package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_1;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_2;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoCcontract_2 {
    @Bean
    public static Item item2(){
        System.out.println("IoCcontract-2 : Create Item2");
        return new ItemImpl_2();
    }
}
