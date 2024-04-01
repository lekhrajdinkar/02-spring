package com.lekhraj.java.spring.SpringCore.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Store implements
        BeanNameAware, ApplicationContextAware,
        InitializingBean, DisposableBean
{
    static Logger log = LoggerFactory.getLogger(Store.class);
    String beanName;
    ApplicationContext context;
    @Autowired ApplicationContext contextAgain; //better

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
        log.info("Store :: constructor-1(Item) :: name-item1_again, hashcode-{}", item1_again.hashCode());
        this.item11 = item1_again;
    }

    @Autowired // DI Auto : 3.3 - It will override to item2
    public void setItem( @Qualifier("item2") Item i){ // <<< arg name , if Qualifier not mentioned
        log.info("Store :: Setter(Item) :: name-item2, hashcode:{}", i.hashCode());
        this.item11 = i;
    }

    @Override
    public String toString() {
        return "\n -------" + storeName + " ------" +
                "\n\tName : "+ item11.getName()+
                "\n\tCode : "+ item11.getCode()+
                "\n\tdetail : "+ item11.getDetail()+
                "\n--------------------------------";
    }

    // =====================================
    //          Life Cycle
    // =====================================
    // 1. initWithConstr>>propertySet>>
    // 2. AwareInterface>> Also inject/wire
    // 3. BeanPostProcesser>> life cycle hooks
    // 4. @overide InitializingBean.afterPropertiesSet() << @postContruct init-1(){}
    //              ---AVIALABLE FOR USE---
    // 5. @overide DisposableBean.  destroy()            << @Predestroy   cleanup-1(){}

    @Override public void setBeanName(String name) { this.beanName = name; }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Autowired // this construtor currenly not getting called. Added for reference.
    public Store(ApplicationContext applicationContext) {
        log.info("Store :: constructor-2(ApplicationContext)");
        this.context = applicationContext;
    }
    @PostConstruct
    void postConstruct(){ log.info("Store :: LifeCycle :: postConstruct()");}

    @PreDestroy
    void predestroy(){ log.info("Store :: LifeCycle :: PreDestroy()");}


    @Override
    public void afterPropertiesSet() throws Exception {log.info("Store :: LifeCycle :: InitializingBean.afterPropertiesSet()"); }

    @Override
    public void destroy() throws Exception { log.info("Store :: LifeCycle :: DisposableBean.destroy()");}
}
