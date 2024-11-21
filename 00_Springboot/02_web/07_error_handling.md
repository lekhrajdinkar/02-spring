# Global Error handling

## A. in `spring MVC project`
### 1. send html response for specific error code - 404
- add html page --> resource/templates/404.html 
- add property **server.error.404 = /error/path-404**
- add controller.
  - extract `ErrorAttributes` from  `WebRequest`
```
@Controller
public class ErrorController
{
    @Autowired  ErrorAttributes errorAttributes    <<<
    
    @RequestMapping("/error/path-404")
    public String handle404Error(WebRequest webRequest) 
    {
        ErrorAttributeOptions options = ErrorAttributeOptions.of(
            ErrorAttributeOptions.Include.MESSAGE, 
            ErrorAttributeOptions.Include.EXCEPTION
            );
        
        Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(webRequest, options);
        // errorAttributes has exception detail, which can be include in html page.
        // add it to model object.
        // in html - ${errorAttribite.xxxxx}
        
        return "404"; //view name
    }
}
```

---
## B. in `REST API`
### default handling flow
- when an exception occurs, it is automatically routed to below path.
- define **server.error.path** = /error
- BasicErrorController is mapped to this path. 
- it processes and send out json response
- ```
  // Sample resposne:
  
  {
  "timestamp": "2024-11-20T00:00:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "No message available",
  "path": "/some-endpoint"
  }
  ```

### customize-1
- note: don't define server.error.404,etc
- when **any Exception** occurs, it is automatically routed to below path
- define **server.error.path** = /my-error-path 
- or just keep /error
- add **new** @RestController for above path
  - extract **ErrorAttributes**  from **webRequest**. like above.
  - will send out json response.

### customize-2 (@ControllerAdvice)
- can have **different handler for different exception** type.
  - @ExceptionHandler(Exception.class) RE<> m1(Exception e, WebRequest request) {...}
  - @ExceptionHandler(Exception2.class) RE<> m1(Exception2 e, WebRequest request) {...}
  - ...

### Customize-3 :: disable tomcat Whitelabel-ErrorPage
- @EnableAutoConfiguration(**exclude** = {`ErrorMvcAutoConfiguration.class`}) --> shows Tomcat page then.
- or, **server.error.whitelabel.enabled=false**