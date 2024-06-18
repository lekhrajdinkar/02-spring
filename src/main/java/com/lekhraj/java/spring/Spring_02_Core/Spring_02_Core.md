https://www.baeldung.com/spring-tutorial
https://www.baeldung.com/spring-dependency-injection
https://chat.openai.com/c/33d0d06d-0aaa-4791-bce9-da8ea3f5dd53
==================================================================

1. IoC, DI, AOP Core concept.
1.1. Custom Annotation
2. DI is pattern/design for IoC.
3. IoC container - Bean + LifeCycle.
3.1. Bean Pre/Post Processor
3.2. Bean Scopes.
4. DI
4.1.manual - Setter and Construtor.
4.2.Autowire (3 places). https://www.baeldung.com/spring-autowire
4.2.1. Resolve - Type, @Qualifier, Custom Qualifier,  Beanname,
5. @Bean, @Component, @Configuration, @R, @S, @C,
   @order(1), @DependsOn("beanName")
   @Autowired vs @Resource - same. resource first check by name. also from JEE specification.
5. @primary, @Qaulifier
6. @value - Property Injection.
7. Spring profile.
8. AOP program.
9. Autowire more
    - map, list, set
    - ResolvableType class for  superclass, interface, generic types.
10. @lookup - handle scope mismatch.
    Singlebean <-- inject prototype bean.
    Spring does not maintain that prototype bean. create memeory leak if not cleaned by manually.

    @Component
    public class MySingletonBean {
        @Lookup
        public MyPrototypeBean getPrototypeBean() {
            // This method body will be ignored
            return null;
        }
    }


10.  @SpringBootApplication  --> @Configuration, @EnableAutoConfiguration, and @ComponentScan.
11. Load bean By profile
12. @Singleton

--- More ---
11. sceduled task
======
1. prototype bean instances are not managed by the container after they are created.
2. In general, it's recommended to avoid injecting prototype beans into singleton beans
if the prototype bean's lifecycle is not managed correctly, as it can lead to unexpected behavior and memory leaks.

@order / I::Ordered:getOrder()
- collection - insertion order.
- multiple Aspect - Apply order.
- load order in container.

## Pending
https://www.baeldung.com/spring-exceptions