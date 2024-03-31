package com.lekhraj.java.spring.SpringCore.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Store {
    static Logger log = LoggerFactory.getLogger(Store.class);

    //=======================================================
    // DI-Auto :: here found 3 same Bean,
    // same type but 3 diff names -item1,item2,item1_again
    //=======================================================

    @Autowired // DI Auto : 3.1
    @Qualifier("item1")
    public Item item11; // <<< name imp
    public String storeName;

    @Autowired
    // DI Auto : 3.2 - never invoked.
    // Because Store is already created with Default constructor.
    public Store(@Qualifier("item1_again") Item item1_again){ // <<< arg name , if Qualifier not mentioned
        log.info("Store :: constructor(Item) :: name-item1_again, hashcode-{}", item1_again.hashCode());
        this.item11 = item1_again;
    }

    @Autowired // DI Auto : 3.3 - It will override to item2
    public void setItem( @Qualifier("item2") Item item2){ // <<< arrg name , if Qualifier not mentioned
        log.info("Store :: Setter(Item) :: name-item2, hashcode:{}", item2.hashCode());
        this.item11 = item2;
    }

    @Override
    public String toString() {
        return "\n ###### " + storeName + " ##### " +
                "\n\tName : "+ item11.getName()+
                "\n\tCode : "+ item11.getCode()+
                "\n\tdetail : "+ item11.getDetail()+
                "\n=========================================";
    }
}
