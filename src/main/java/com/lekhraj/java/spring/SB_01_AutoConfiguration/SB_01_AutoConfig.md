https://www.baeldung.com/spring-boot-annotations

- Conditionally Apply AutoConfiguration
1. @ConditionalOnClass and @ConditionalOnMissingClass
2. @ConditionalOnBean and @ConditionalOnMissingBean
3. @ConditionalOnProperty
4. @ConditionalOnResource
5. @ConditionalOnWebApplication and @ConditionalOnNotWebApplication
6. @ConditionalExpression
7. @Conditional
8. etc ,more

Apply these on
- @Configuation class
- @Bean method

Starter project > spring.factories=listOfClass (which can be applied conditioncally)
SpringApplication.run() > load AC,all bean, if webApp then ...

Create Custom starter project
=============================
1. create new Spring project.
2. Create @Configuration  Auto-Configuration Class --> with Bean with conditions ,to enable/disable
    auto-configuration which we were doing manually before
3. Register  Auto-Configuration Class
    resource/META-INF/spring.factories with configuration class list.
4. package : mvn clean package/install --> local Maven repo.
4. use it other project.

