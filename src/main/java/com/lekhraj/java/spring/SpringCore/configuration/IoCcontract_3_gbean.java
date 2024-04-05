package com.lekhraj.java.spring.SpringCore.configuration;

import com.lekhraj.java.spring.SpringCore.bean.Item;
import com.lekhraj.java.spring.SpringCore.bean.ItemImpl_2;
import com.lekhraj.java.spring.SpringCore.bean.MyGenericBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IoCcontract_3_gbean
{
    // @Bean MyGenericBean gbean_1(){ return new MyGenericBean<Integer, ItemImpl_2>();}
    @Autowired
    ItemImpl_2 item2;
    @Bean
    MyGenericBean gbean_1(){
        return new MyGenericBean<Integer, ItemImpl_2>(100, item2);
    }
}
