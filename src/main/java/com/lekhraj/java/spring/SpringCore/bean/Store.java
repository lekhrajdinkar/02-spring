package com.lekhraj.java.spring.SpringCore.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Store {
    static Logger log = LoggerFactory.getLogger(Store.class);

    public Item item1;
    public String storeName;

    public Store( Item i){
        log.info("Store :: default constructor");
        this.item1 = i;
    }

    @Override
    public String toString() {
        return "\n ###### " + storeName + " ##### " +
                "\n== Item-1 == " +
                "\n\tName : "+item1.getName()+
                "\n\tCode : "+item1.getCode()+
                "\n\tdetail : "+item1.getDetail()+
                "\n=========================================";
    }
}
