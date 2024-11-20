- https://chatgpt.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5 
--- 
# sb-starter-web
## feature 
- necessary dependencies (jackson, springMVC), 
- embedded server
- default exception
- Actuator

---
## WebServerFactoryCustomizer class
- interface provided by Spring Boot 
- allows you to customize the configuration of embedded web servers
  - setting ports, 
  - enabling SSL, 
  - configuring timeouts

```
@Component
public class MyTomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) 
    {
        factory.setPort(8081);  
        factory.setContextPath("/myapp");  

        // Additional customization can be done here
        factory.addConnectorCustomizers(connector -> {

        });
        
        // SSl
        Ssl ssl = new Ssl();
            ssl.setKeyStore("classpath:keystore.jks");
            ssl.setKeyStorePassword("password");
            ssl.setKeyPassword("password");
        factory.setSsl(ssl);
    }
}
```
---
## ServletRegistrationBean
- Register a New Servlet (spring boot way)

```
@Configuration
public class ServletConfig 
{
    @Bean
    public ServletRegistrationBean<CustomServlet> customServletRegistrationBean() 
    {
        ServletRegistrationBean<CustomServlet> registrationBean = new ServletRegistrationBean<>(new CustomServlet(), "/custom");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
}
```
---
## Typical things:
### update embedded server
- update pom 
  - add spring-boot-starter-**undertow** / spring-boot-starter-**jetty**
  - exclude <spring-boot-starter-**tomcat** from `spring-boot-starter-web`

### shut down
- ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
- context.close();