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

    @Autowired // DI Auto : 3.1
    // @Qualifier("item1") - works, if found multiple implementation.
    // here found 3 same implemenations
    public Item item1_again;
    public String storeName;

    //@Autowired // DI Auto : 3.2 - never invoked . why ????
    public Store(Item item1){
        log.info("Store :: constructor(Item) :: Item:{}", item1);
        this.item1_again = item1;
    }

    // @Autowired // DI Auto : 3.3
    public void setItem1_again(Item item2){
        log.info("Store :: constructor(Item) :: Item:{}", item2);
        this.item1_again = item2;
    }

    @Override
    public String toString() {
        return "\n ###### " + storeName + " ##### " +
                "\n\tName : "+ item1_again.getName()+
                "\n\tCode : "+ item1_again.getCode()+
                "\n\tdetail : "+ item1_again.getDetail()+
                "\n=========================================";
    }
}
