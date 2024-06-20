## Customization Examples:

### Group-1
1. Register Global Servlet/ filter
 - @Bean `ServletRegistrationBean<MyServlet>`
 - @Bean `FilterRegistrationBean<MyFilter>` 
 - or, MyFilter extends org.springframework.web.filter.`GenericFilterBean` then add it SecurityFilterChain bean, using `addFilterBefore/After()`.
```
    registrationBean.setFilter/Servlet(new MyFilter()/MyServlet());
    registrationBean.addUrlPatterns("/*");
```

2. Change from Tomcat to jetty/undertow servers.
   - Add/remove Dependencies
   - Add @Bean to modify ServletContainer(port,etc) - UndertowEmbeddedServletContainerFactory, JettyEmbeddedServletContainerFactory.

3. Remove any embedded Server / Deploy on external Server
- https://chatgpt.com/c/f4a0c9cd-c6cb-414e-888c-605c2d50340c
- remove dependencies.
- <packaging>war</packaging>
- extend `SpringBootServletInitializer` -> override Configure.
   ```
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
   }
   ```
4. Customize Whitelabel-ErrorPage
    - @EnableAutoConfiguration(exclude = {`ErrorMvcAutoConfiguration.class`}) --> show Tomcat page then.
    - server.error.whitelabel.enabled=false --> show Tomcat page then.
    - /error --> map it myController and return custom ResponseEntity.
      - Default :: /error is mapped to `BasicErrorController`
      - @Controller MyErrorController class >> @RequestMapping("/error")m(){ return RE }
      - inject ErrorAttribute, which will help to fill RE.

---
### Group-2 
> com.lekhraj.java.spring.SB_99_RESTful_API.configuration.webconfig

1. org.springframework.web.`WebServerFactoryCustomizer<WS>` 
  - FunctionalInterface :)
  - implement and override : `customize(XXXXXWebServerFactory factory)`
  - customize the configuration of `embedded-web-servers` like port, rootContext, SSL, etc

2. org.springframework.web`WebApplicationInitializer` 
   - Implement and override : `onStartup(ServletContext servletContext)`
   - basically to configure a `ServletContext` programmatically.

3. org.springframework.web.`WebMvcConfigurer`
   - CORS, etc
