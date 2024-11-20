- https://chat.openai.com/c/d75560a0-1c06-4195-80fc-563ec8449bc5
- https://www.baeldung.com/configuration-properties-in-spring-boot
---
# Application properties

## 1. Register
### Automatically registered files:
- application.property - default file
- application-<profile/env>.properties

### Register custom properties-file
- use `@propertySource / @propertySources`
- keep here - **src/main/resources** : sb has utility method to read from resource folder.
- check : [ReadConfigFromGlobalProperty.java](..%2FSpring_03_Properties%2Fconfiguration%2FReadConfigFromGlobalProperty.java)
- can have env/profile specific files too, as shoen below.
```
@Configuration  
@PropertySources(value = {
        @PropertySource(value = "classpath:global-database-${spring.profiles.active}.properties"),
        @PropertySource(value = "classpath:global-rabbit-mq-${spring.profiles.active}.properties")
})
```
- external property files
```
1. JVM ARG :: java -jar my-app.jar --spring.config.location=<path>/application.properties
2. env var :: export SPRING_CONFIG_LOCATION=/config/external/application.properties
3. Command Line Arguments  :: ?
```

## 2. Binding
- **property** --> sb does **binding** bts --> java-Object / primitive type
- **@ConfigurationProperties**
```
@ConfigurationProperties(prefix = "mail")
@ConfigurationPropertiesScan // register bean
public class ConfigurationPropertiesByPrefixBean
{
    ...
}
```
```
// Using @ConfigurationProperties on a @Bean Method
// pending...
```
- more ways to **register** above bean - ConfigurationPropertiesByPrefixBean
  - EnableConfigurationProperties({c1.class, c2.class}) --> enable/register explicitly, may be in Application.java file
  - add @ConfigurationPropertiesScan Apply on class c1/c2 (done above)
  - add @Component
  - add @Configuration

### inbuilt binding
- property name must match - b/w prop and java class fields.
```
all these works
- mail.hostName
- mail.hostname
- mail.host_name
- mail.host-name
- mail.HOST_NAME
```


### custom binding
- **@ConfigurationPropertiesBinding**, custom binding rule/code.
  - implement Converter<S,T> --> @override T convert(S)
  - https://www.baeldung.com/configuration-properties-in-spring-boot
  - check this: mail.credentials2=john2,password2 to ConfigurationPropertiesByPrefixBean
  - validation (JSR-380 format) while binding.

- PropertySourcesPlaceholderConfigurer

## 3. Read properties
- `@Autowired Environmnet` env.
- `@value` 
  - inject string
  - inject boolean
  - inject number types
  - SpEL
  - inject object
  - inject collection - map
  - inject collection - list/set
- check : [ConfigurationPropertiesByPrefixBean.java](..%2FSpring_03_Properties%2Fbean%2FConfigurationPropertiesByPrefixBean.java)

