package com.lekhraj.java.spring.SpringCore.bean;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SingletonBean {
    //ProtoypeBean pbean;

    // Everytime call getPbean(), Spring will  create and return new object.
    @Lookup
    public ProtoypeBean getPbean() {
        //return pbean;
        return null;
    }
}
