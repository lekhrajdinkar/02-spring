- https://chatgpt.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5 
--- 
# sb-starter-web
## feature 
- necessary dependencies (jackson, springMVC), 
- embedded server
- default exception

## WebServerFactoryCustomizer
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