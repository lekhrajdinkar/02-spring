- web
  - https://chatgpt.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5 - web kickoff
  - https://chatgpt.com/c/7d23b0fe-a7a5-43d5-9ced-69d4a344e31a - error handling
  - https://chatgpt.com/c/f4a0c9cd-c6cb-414e-888c-605c2d50340c - ext server deploy
  - https://chatgpt.com/c/efc733ec-0a20-4be2-88da-df50535517b3 - MVC overview
  - https://chatgpt.com/c/d7dba5ab-7f7a-4c1c-a443-f67f15ca09a2 - servlet3
- REST
  - https://chatgpt.com/c/0471007c-7d4e-4a04-bd37-d6262d5f9aaf - kickoff
  - https://chatgpt.com/c/7fa2c12d-eada-4991-944f-cfad8d084805 - API version
  - https://chatgpt.com/c/34abc85f-eabb-47ac-b525-7c2c6af8023a - web filter
  - https://chatgpt.com/c/b8c60911-2df5-478b-9804-67c3ecf9506d - validation
  - https://chatgpt.com/c/591c7b06-4ac0-4f03-a328-038cde9cf7ca - validation 2
  - https://chatgpt.com/c/a04dc001-e879-43e0-a39d-acd01b9ef2c7 - bean validation
  - https://chatgpt.com/c/9719e1f6-c4e4-4fac-8941-178c26acc484 - Consume API
  
---
# REST
- https://chat.openai.com/c/831f36a0-bce4-4372-87d4-9ab6528babc5
- @ResponseBody + `@Controller` =` @RestController`
- produces/consumes = MediaType.APPLICATION_JSON
- spring-boot-starter-web (SpringMVC,jackson,Actuator,Tomcat,etc)

- Modify HttpResponse
  - inject `HttpServletResponse response` as method arg directly and manipulate response.
  - ResponseEntity<?> ResponseEntityBuilder -->  statusCode, headers and body.
  - @ResponseBody - serialized into JSON

---

## A. REST : Error handling
- https://chatgpt.com/c/7d23b0fe-a7a5-43d5-9ced-69d4a344e31a
- @Component public class CustomErrorAttributes extends `DefaultErrorAttributes`

case:1 : incoming request failed, then:
-  /error + BasicErrorController (already).
-  /my-error + MyBasicErrorController (custom) + inject ErrorAttribute. //server.error.path
-  can have CustomBean DefaultErrorAttributes
   > check : com.lekhraj.java.spring.SB_99_RESTful_API.controller.CustomErrorAttributes

case-2 : incoming requested success, but business code failed with Exception.
- Global Exception Handling
  - @ControllerAdvice/@RestControllerAdvice + @ExceptionHandler(Exception.class)
  - check ResourceNotFoundException

---

## B. REST : Validation
- on DTO/Bean/ENTITY
- https://chatgpt.com/c/a04dc001-e879-43e0-a39d-acd01b9ef2c7
- Add <artifactId>spring-boot-starter-validation</artifactId>
- @email, @size, @NotBlank(""), @CustomAnnotationAsWell, etc
- use @Valid annotation to trigger validation in Controller or where ever binding happens:
    - @ResquestBody binding
    - Apply on method, which in turn applied on return value.
  
- optional : inject BindingResult.
- check : Custom validator : check com.lekhraj.java.spring.SB_99_RESTful_API.hibernate_validator;
- `ResponseBodyAdvice` : 
  - The default Spring validation mechanism does not automatically validate objects wrapped in ResponseEntity. 
     However, you can achieve this by creating a custom ResponseBodyAdvice implementation.
  - @Valid / @validated :: cannot perform on ResponseEntity.

---

## C. REST : Documentation
- springfox-boot-starter + @EnableSwagger2WebMvc + com.lekhraj.java.spring.SB_99_RESTful_API.configuration.SwaggerDocConfig - @Bean Docket
- Springdoc OpenAPI+ @EnableSwagger2 + com.lekhraj.java.spring.SB_99_RESTful_API.configuration.SpringDocOpenApiCopnfig - @Bean Docket
- `<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>` : worked
- Annotation:
  - @Tag/@Api, 
  - @ApiOperation, 
  - @ApiResponse, 
  - @ApiParam.
- */swagger-ui.html (default)

---

## D. REST : Binding ( Serialize/De-serialize)
- binding happens with internal Serialize/De-serialize, can create custom ones. 
- String to LocalDateTime : @DateTimeFormat (From SB, not jackson)
- Bind `Map<String,String>` with
  - RequestHeader
  - RequestBody
  - PathVariable
  - RequestParam
  > com.lekhraj.java.spring.SB_99_RESTful_API.controller.JewelleryController
  >  - @PathVariable - make optional, can not default value
  >  - @RequestHeader, @RequestParam ?kv&kv - make optional, set default.

- jackson : ObjectMapper - Custom `Serializer / De-Serializer`
  - @Configuration JacksonConfig >> @Bean ObjectMapper (global)
  - setSerializationInclusion(JsonInclude.Include.NON_NULL)
  - Serialize LocalDateTime in custom Serializer.
    > - create : com.lekhraj.java.spring.SB_99_RESTful_API."Serializer".LocalDateTimeSerializer
    > - then, register it with ObjectMapper.
    > - @ResponseBody use Global Objectmapper, check `jacksonConfig`
  
- HttpMessageConverter
  - new MappingJackson2HttpMessageConverter()
---
## E. REST API Version
- https://chatgpt.com/c/7fa2c12d-eada-4991-944f-cfad8d084805
  - @RequestMapping("/api/v1")
  - @GetMapping(value = "/users", params = "version=1")
    - while consuming, set requestParam :  version=1
  - @GetMapping(value = "/users", headers = "X-API-VERSION=1")
    - while consuming, set header : X-API-VERSION=1
  - @GetMapping(value = "/users", produces = "application/vnd.company.app-v1+json")
    - while consuming, set header : Accept=application/vnd.company.app-v1+json

  - Choosing the Right API versioning Approach:
    - URI Path Versioning: Clear and straightforward, widely used.
    - Request Parameter Versioning: Useful for flexibility in request parameters.
    - Header Versioning: Keeps the URL clean, suitable for clients that can easily add headers.
    - Content Negotiation Versioning: Good for complex API evolution but can be harder to debug.

---

## Y. REST : More
- https://www.baeldung.com/rest-with-spring-series
- PUT : C & U Idempotent (no impact, if run multiple times).
- Consume ::
  1. RestTemplate - with basicAuth and token
  2. webClient

---
## Z. REST : Exception and Error 

- No value extractor found for type parameter 'T' of type org.springframework.http.ResponseEntity
- cause
  - This problem can arise if you are using validation annotations (like @Valid or @Validated) on a ResponseEntity<T> directly, 
    which Hibernate Validator doesn't support out of the box.
- Fix:
  - Avoid Validation on ResponseEntity: Generally, you shouldn't be validating ResponseEntity directly. Instead, validate the actual content inside the ResponseEntity.
  - Custom Value Extractor: If you absolutely need to validate ResponseEntity<T>, you can create a custom ValueExtractor for ResponseEntity<T>.

---

# Pending
- SetTimeouts API
- API to download file.
- Filters and Interceptors / `InterceptorRegistry`
- webClient and RestTemplate
- custom Binder
- ResponseBodyAdvice program
- `@WebFilter`
- Send response other than JSON





