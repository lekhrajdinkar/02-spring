- Json , JsonStr , Object , JsonNode(asText(), readTree())
- Objectmapper / Xmlmapper API
- @ResquestBody and @responseBody behind the scene perform S and D using OM.
- https://www.baeldung.com/jackson
- Object mapper : https://chatgpt.com/c/4334405c-e8c0-4e88-a138-a53b14ae52b5
---

## Common task
1. Serialize and Deserialize
2. property order, alias, root
2. Type/property  : include / ignore
3. formatting : date, time, number, etc : 
   - @DateTimeFormat(fromSB)
   - om.setDateFormat(new SimpleDateFormat(" "))
4. handle collection.
```
    - String jsonCarArray ="[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
      List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
      
    - String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
      Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
```
5. handle raw json
6. handle Enums

## Advance task
1. inject
2.

---

## Objectmapper
- Builder pattern
### Serialize
- writeValue
- writeVlaueAsString

### De-serialize
- readvalue (json, TypeReference<T>)

### Config
- unknown/incomplete
    - FAIL_ON_UNKNOWN_PROPERTIES , @JsonIgnoreProperties(ignoreUnknown = true) MyClass
    - FAIL_ON_NULL_FOR_PRIMITIVES : unmarshalling an incomplete JSON :  @JsonProperty(required = false.True)
  
- on null value
- WRITE_DATES_AS_TIMESTAMPS,
- DEFAULT_VIEW_INCLUSION,
- INDENT_OUTPUT 
- OM >> register module >> add custom S and D
- OM.configure(k,v)
- on DTO >> Apply annotation
  - https://chatgpt.com/c/0f8b8a16-cab5-4fbb-8f0e-1c7949dd97f4
  - https://chatgpt.com/c/cbe3f6c3-c238-4db8-99e5-a0c9c9ddffec

---
- UnrecognizedPropertyException : objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
- extends Std/JsonSerializer<Car> --> serialize(T object, JsonGenerator jsonGenerator, SerializerProvider serializer) {...}
-  extends Std/JsonDeserializer<Car>  --> T deserialize(JsonParser parser, DeserializationContext deserializer) {...}
- @JsonCreator : Apply on contructor (@jsonproperty on arg). that constructor is used while unmarshalling




