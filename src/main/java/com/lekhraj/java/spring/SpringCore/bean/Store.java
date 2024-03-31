package com.lekhraj.java.spring.SpringCore.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Item item1; // <<< name imp
    public String storeName;

    @Autowired // DI Auto : 3.2 - never invoked. Because Store is already created with Default constructor.
    public Store(Item item1_again){ // <<< arg name imp
        log.info("Store :: constructor(Item) :: name-item1_again, Item:{}", item1_again);
        this.item1 = item1_again;
    }

    //@Autowired // DI Auto : 3.3 - It will override to item2
    public void setItem(Item item2){ // <<< arg name imp
        log.info("Store :: Setter(Item) :: name-item2, Item:{}", item2);
        this.item1 = item2;
    }

    @Override
    public String toString() {
        return "\n ###### " + storeName + " ##### " +
                "\n\tName : "+ item1.getName()+
                "\n\tCode : "+ item1.getCode()+
                "\n\tdetail : "+ item1.getDetail()+
                "\n=========================================";
    }
}
