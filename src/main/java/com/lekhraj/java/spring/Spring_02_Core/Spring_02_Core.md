# Spring - Core
- https://www.baeldung.com/spring-tutorial
- https://www.baeldung.com/spring-dependency-injection
- https://chat.openai.com/c/33d0d06d-0aaa-4791-bce9-da8ea3f5dd53
---

## Dependency Injection
- `IoC` : inversion of control of create object from Code to configuration(metadata)
- `DI` id pattern/design for IoC.
- create beans:
  - Beans (Scopes : singleton/prototype )
  - @`Configuration` >> @`Bean` m(){ return new object() ;}
  - @`Component`, [ Stereotype: @`Repository`, @`Service`, @`Controller` ] 
- Inject Dependency
  - manual
    - Constructor
    - Setter : override
  - @`Autowired` : https://www.baeldung.com/spring-autowire
    - apply on `property` 
    - apply on `method` --> get applied to `method arg`, then.
    - issue while autowire:
      - `no-bean` found.
      - `multiple-bean` found (Conflict) 
        - resolve :: Type >>  @`Qualifier`(BeanName) >> Custom-Qualifier | @`primary`
      - Circular dependencies:
        - @order(1), @DependsOn("beanName")
        - refactor code
        - avoid using constructor Injection
    - Autowire more
      - map, list, set
      - ResolvableType class for  superclass, interface, generic types. 

- More:
  - `Spring profile` : local, dev,qa,prod | @profile()
  - @`lookup` 
    - handle scope mismatch.
    - SingleBean <-- inject prototype bean.
    - it will not create new instance of proto member.
    - if we write custom code get new instance, spring won't manage it and could create memory leak, if not cleaned by manually.
    - solution : use @Lookup
    - it's recommended to avoid injecting prototype beans into singleton beans
    - ```
      @Component
      public class MySingletonBean {
           @Lookup
           public MyPrototypeBean getPrototypeBean() {
                 // This method body will be ignored
                  return null;
           }
      }
      ```  
  - Spring IOC container:
    - `Life Cycle`.
    - `Bean Processor` - Pre and Post
  - @`SpringBootApplication`  --> @Configuration, @EnableAutoConfiguration, and @ComponentScan.  
  - @`Singleton`
  - @`Order` meanings:
    - collection - insertion order.
    - multiple Aspect - Apply order.
    - load bean in container.

---
## Code    
1. sceduled task

---
## AOP
- Core concept.
- create Custom Annotation
---

## Pending
https://www.baeldung.com/spring-exceptions