#  WEB / REST
- spring-boot-starter-web (SpringMVC,jackson,Actuator,Tomcat,etc)
- https://chat.openai.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5
- RESt API Vesrion : https://chatgpt.com/c/7fa2c12d-eada-4991-944f-cfad8d084805 Example:
    - @RequestMapping("/api/v1")
    - @GetMapping(value = "/users", params = "version=1")
    - @GetMapping(value = "/users", headers = "X-API-VERSION=1")
    - @GetMapping(value = "/users", produces = "application/vnd.company.app-v1+json") : Accept header
- Choosing the Right API vesrioning Approach:
  - URI Path Versioning: Clear and straightforward, widely used.
  - Request Parameter Versioning: Useful for flexibility in request parameters.
  - Header Versioning: Keeps the URL clean, suitable for clients that can easily add headers.
  - Content Negotiation Versioning: Good for complex API evolution but can be harder to debug.
---

## 1 Error handling in web.
https://chatgpt.com/c/7d23b0fe-a7a5-43d5-9ced-69d4a344e31a

case:1 : incoming request failed, then:

-  /error + BasicErrorController (already).
-  /my-error + MyBasicErrorController (custom) + inject ErrorAttribute.
-  WebServerFactoryCustomizer *

case-2 : incoming requested success, but business code failed with Exception.

Global Exception Handling
- @ControllerAdvice/@RestControllerAdvice + @ExceptionHandler(Exception.class)
- check ResourceNotFoundException

## Validation in SB
- on DTO/Bean/ENTITY
- https://chatgpt.com/c/a04dc001-e879-43e0-a39d-acd01b9ef2c7
- Add <artifactId>spring-boot-starter-validation</artifactId>
- @email, @size, @NotBlank(""), @CustomAnnotationAsWell, etc
- use @Valid annotation to trigger validation in Controller or where ever binding happens:
    - @ResquestBody binding
    -
- optional : inject BindingResult.
- check : Custom validator : check com.lekhraj.java.spring.SB_99_RESTful_API.hibernate_validator;
- ResponseBodyAdvice :
    The default Spring validation mechanism does not automatically validate objects wrapped in ResponseEntity.
    However, you can achieve this by creating a custom ResponseBodyAdvice implementation.

## REST Documentation
- springfox-boot-starter + @EnableSwagger2WebMvc + com.lekhraj.java.spring.SB_99_RESTful_API.configuration.SwaggerDocConfig - @Bean Docket
- Springdoc OpenAPI+ @EnableSwagger2 + com.lekhraj.java.spring.SB_99_RESTful_API.configuration.SpringDocOpenApiCopnfig - @Bean Docket
- <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId> : worked
- @Tag/@Api, @ApiOperation, @ApiResponse, @ApiParam.
- */swagger-ui.html (default)

---

## Some programs
### 1. Register a New Servlet
 - @Bean ServletRegistrationBean<MyServlet>

### 3. Change from Tomcat to jetty/undertow servers.
   - Add/remove Dependencies
   - Add @Bean to modify ServletContainer(port,etc) - UndertowEmbeddedServletContainerFactory, JettyEmbeddedServletContainerFactory.

### 3. Remove embedded Server / Deploy on external Server
- https://chatgpt.com/c/f4a0c9cd-c6cb-414e-888c-605c2d50340c
 - remove dependencies.
 - <packaging>war</packaging>
 - extend SpringBootServletInitializer -> override Configure.
    ```
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
         return application.sources(DemoApplication.class);
    }
    ```

### 4. "SB Auto config Customization"

#### 4.1. Whitelabel-ErrorPage
    - @EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class}) --> show Tomcat page then.
    - server.error.whitelabel.enabled=false --> show Tomcat page then.
    - @Controller MyErrorController class >> @RequestMapping("/error")m(){ my page code} --> custom my page.

#### 4.2. ObjectMapper - Custom Serializer
    @Configuration JacksonConfig >> @Bean ObjectMapper
       - setSerializationInclusion(JsonInclude.Include.NON_NULL)
       - Serialize LocalDateTime in custom Serializer.
         com.lekhraj.java.spring.SB_99_RESTful_API."Serializer".LocalDateTimeSerializer


---
## https://www.baeldung.com/rest-with-spring-series
- PUT : C & U Idempotent (no impact, if run multiple times).

### Binding
    - String to LocalDateTime : @DateTimeFormat
    - use Map<S,S> to bind all RequestHeader,PathVariable, RequestParam
    - custom Binder ???? custom anno ?
  
### Modify HttpResponse
    - ResponseEntity<?> Builder -->  status-code, headers and body.
    - inject "HttpServletResponse response" as method arg directly and manipulate response.
    - note: @ResponseBody + @Controller = @RestController
    - automatically serialized into JSON
    - produces/consumes = MediaType.APPLICATION_JSON
    - @Valid / @validated :: cannot perform on ResponseEntity.
  
### Check com.lekhraj.java.spring.SB_99_RESTful_API.controller.JewelleryController
    - @PathVariable - make optional, can not default value
    - @RequestHeader, @RequestParam ?kv&kv - make optional, set default.

### Entity to DTO ???
```
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
```
//todo
- SetTimeouts API
- API to download file.
- Filters and Interceptors
- webClient and RestTemplate
- custom Binder

---
# Error 

- No value extractor found for type parameter 'T' of type org.springframework.http.ResponseEntity
- cause
  - This problem can arise if you are using validation annotations (like @Valid or @Validated) on a ResponseEntity<T> directly, 
    which Hibernate Validator doesn't support out of the box.
- Fix:
  - Avoid Validation on ResponseEntity: Generally, you shouldn't be validating ResponseEntity directly. Instead, validate the actual content inside the ResponseEntity.
  - Custom Value Extractor: If you absolutely need to validate ResponseEntity<T>, you can create a custom ValueExtractor for ResponseEntity<T>.







