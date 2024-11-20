# Error handling

## A. in `spring MVC project`
## 1. send html response for specific error code - 404
- add html page --> resource/templates/404.html 
- add property **server.error.404 = /error/path-404**
- add controller.
  - extract `ErrorAttributes` from  `WebRequest`
```
@Controller
public class ErrorController
{
    @Autowired  ErrorAttributes errorAttributes
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
## A. in `REST API`
### way-1
- don't define server.error.404,etc
- define **server.error.path** = /all-error-path
- add @RestController for above path
  - extract ErrorAttributes  from webRequest. like above.
- will send out json response.

### way-2 - @ControllerAdvise


