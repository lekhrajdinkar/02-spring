# Spring - Core
- bauleng:
  - https://www.baeldung.com/spring-tutorial
  - https://www.baeldung.com/spring-dependency-injection
  - https://www.baeldung.com/spring-exceptions
- chatgpt 
  - question: https://chat.openai.com/c/33d0d06d-0aaa-4791-bce9-da8ea3f5dd53
---

## A. Dependency Injection
### key concept:
- `dependency`
- `IoC` : inversion of control of create object from Code to configuration(metadata) + resolve dependency
- `DI` : pattern/design for IoC.
- `Beans` 
  - Scopes : singleton / prototype 
--- 
### developer primary 2 tasks

> don't use xml config at all. think of java config only.

- **task-1**: create/define beans in meta-config 
  - way-1: `@Configuration` > `@Bean`
  - way-2: `@Component`
    - `@Repository`
    - `@Service` 
    - `@Controller`
  
- **task-2**: Inject Dependency
  - **manual**
    - `@Configuration`
      - `@Bean` m(){ return new object() ;}
      - `@Bean` m( bean1 b1 ){ bean2 o = new object(); o.setb1(b1) ; return o; }  -  **setter injection (manual)** :point_left:
      - `@Bean` m( bean1 b1 ){ bean2 o = new object(b1);  return o; } -  **contructor injection (manual)** :point_left:
      
  - **Autowire** - `@Autowired` 
    - https://www.baeldung.com/spring-autowire
    - ResolvableType class for  superclass, interface, generic types.
    - apply on :
      - `property` 
      - `method` --> get applied to `method arg`, then.
      - `constructor` --> get applied to `constructor arg`, then.
  - Autowire more
    - issue while autowire:
      - `no-bean` found.
      - `multiple-bean` found (Conflict) 
        - resolve :: Type >>  @`Qualifier`(BeanName) >> Custom-Qualifier | @`primary`
      - `Circular dependencies`:
        - use @`order`(1) 
        - use @`DependsOn`("beanName")
        - refactor code
        - avoid using constructor Injection
      - inject - map, list, set
      ```
        - create @Bean b1, b2, b3, of Bean1 type
        - @Autowire list<Bean1> listofbean1 --> inject/add b1,b2,b3 into listofbean1
      ```
---
## B. Annotation
### `@profile()`
- bean will create for that profile
- Spring profile : local, dev,qa,prod
- app.prop --> add this spring.profiles.active=local
- env var : SPRING_PROFILES_ACTIVE

### `@lookup` 
- handle scope mismatch.
- scenario-1 : inject SingleBean --> prototype bean : ok
- scenario-2 : SingleBean <-- inject prototype bean : one issue 
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
### `@Order`
- in context of: 
  - collection :  insertion order.
  - multiple Aspect - Apply order.
  - load bean in container.
  
### `@Singleton`

---

## C. Spring IOC container
### bean Life Cycle
- `Bean Processor` - Pre and Post

---
## D. Code / sample programs   
- sceduled task
- create Custom Annotation using Aop

