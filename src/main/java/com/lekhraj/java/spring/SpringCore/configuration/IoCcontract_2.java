package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_1;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_2;
import com.lekhraj.java.spring.SpringCore.bean.Store;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lekhraj.java.spring.SpringCore.bean")
public class IoCcontract_2 {
    // rename to item1() - bean registration will get failed with this name.
    // Because "item1" name is already taken
    @Bean
    @Qualifier("IoCcontract_2_item2")
    public static Item item2(){
        Item bean = new ItemImpl_1();
        System.out.println("IoCcontract_2 : Bean Created, type-Item, name-item2, hashcode-"+bean.hashCode());
        return  bean;
    }
}
