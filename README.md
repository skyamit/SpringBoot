# RestApi Basics :

## Annotations :

1. @SpringBootApplication 
2. @RestController
3. @AutoWired
4. @RequestBody
5. @PathVariable
6. @ControllerAdvice
7. @ExceptionHandler
8. @Valid
9. @Past
10. @GeneratedValue

## Basic Questions

1. How are our request is handled?
Dispatcher servlet - It handles root path and based on the mapping requested by browser it provides the requested link.
AutoConfiguration (DispatcherServletAutoConfiguration)

2. How does bean got converted to JSON?
@ResponseBody + JacksonHTTPSMessageConverter
AutoConfiguration(JacksonHttpsMessageConverter)

3. Who is configuring Error Mapping?
AutoConfiguration(ErrorMvcAutoConfiguration)

4. How are all the jars available?
When we create starter project then we can choose what all things we want(Spring boot starter web, tomcat, jackson)

5. How exceptions are handled?
ResponseEntityExceptionHandler is the class which handles the exception.

## Path Parameters
```
GetMapping("/somepath/{id}")
public SomeClassName function(@PathVariable("id") long id)
```
## Request Methods 
1. GetMapping - Retrieve Details of resources
2. PostMapping - create a new resources
3. PutMapping - update a exisiting resources
4. PatchMapping - update a part of resources
5. DeleteMapping - Delete a resources

## PostMapping example
```
@PostMapping("/user")
public ResponseEntity<User> addUser(@RequestBody User user)
```
## Reponse Status for RestAPI
1. 200 - success
2. 201 - created
3. 204 - No content
4. 401 - Unauthorized
5. 400 - Bad Request
6. 404 - Resource Not Found
7. 500 - Server Error

## ResponseEntity
```
return new ResponseEntity(Object,HttpStatus.INTERNAL_SERVER_ERROR);
```
## Exception Handling 
```
1. orElse() - If data is not found then we can return other.
2. if(user==null){
      throw new UserNotFoundException("id:"+id);
   }
   
   @ResponseStatus(code=HttpStatus.NotFound)
   class UserNotFoundException extends RuntimeException{
      public UserNotFoundException(String message){
        super(message);
      }
   } 
```

## Custom Exception Structure 
```
 @ResponseStatus(code=HttpStatus.NotFound)
 class UserNotFoundException extends RuntimeException{
   public UserNotFoundException(String message){
      super(message);
   }
 }
   
 public class ErrorDetails{
   private LocalDate timestamp;
   private String message;
   private String details;

   // generate getter, setters, and constructor
 }
 
 @ControllerAdvice
 public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
   
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<ErrorDetails> handleAllException(Exception ex,WebRequest request){
      ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
   }
   
   @ExceptionHandler(UserNotFoundException.class)
   public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex,WebRequest request){
      ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
   }
 }
 
```
## DeleteMapping example :
```
@DeleteMapping("/user/{id}")
public void deleteById(@PathVariable("id") Long id) {
      userServiceImpl.deleteById(id);
}
```

## Validations
### Dependency
```
<groupId>org.springframework.boot</groupId>
<artifactId>Spring-boot-starter-validation</artifactId>
```

### Valid Annotation Example
```
@PostMapping(path="/addUser")
public ResponseEntity<User> addUser(@Valid @RequestBody User user){
      userDao.save(user);
      return new ResponseEntity<User>(user,HttpStatus.OK)
}
```

#### Note - We have to update our User Bean and add constraints to the fields

### Constraints
```
@Size(min=2,message="Name should be atleast 2 characters")
String name;

@Past(message="Birth date should be in past")
LocalDate date;
```

## Advanced API Rest Features

### Rest API Documentation
1. Manually maintain documentation
2. Generate documentation from code

### Swagger and Open API
1. Open API - Standard language Agnostic interface, Discover and Understand Rest API
2. Swagger - Visualize and Interact with your Rest API.

### Library to automate the generation of API Documentation for Spring Boot Rest API:
```
<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.9</version>
</dependency>
```

#### Swagger Link - http://localhost:8080/swagger-ui/index.html
#### Open API Link - http://localhost:8080/v3/api-docs

### Content Negotitation
1. Same Resource and Same URI have different Representations.
2. Different Content Type - XML or JSON...
3. Different Language  - English or Dutch...
4. We can accept head to know what kind of data user expects.
5. Accept Lanugage Header, Accept MIME Header etc...

#### With the help of "FasterXML Jackson DataFormat" we can handle Content Negotiation.
```
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
    <version>2.13.4</version>
</dependency>
```
#### Add header : accept - application/xml or application/json


### Internationalization (I18N)
#### message.properties
```
good.morning.message = Good Morning
```

#### messages_nl.properties
```
good.morning.message = Good Morning in Dutch
```

#### Example :
```
@GetMapping("/hello-world-I18N")
public String helloWordInternationalisation() {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage("good.morning.message",null,"Default Message", locale);
}
```

### Versioning
1. We can implement versioning by creating two paths
   ex : /v1/student and /v2/student
2. We can also implement versioning with the help of params
   ex : /student?version=1 and /student?version=2

```
// Using different path

@GetMapping(path="/v1/student")
public Student version1Student() {
      return new Student1("Bob Charlie");
}

@GetMapping(path="/v2/student")
public Student version2Student() {
      return new Student2("Bob","Charlie");
}

// Using different Parameters (/student?version=1)

@GetMapping(path="/student",params="version=1")
public Student version1Student() {
      return new Student1("Bob Charlie");
}

@GetMapping(path="/student",parms="version=2")
public Student version2Student() {
      return new Student2("Bob","Charlie");
}

// Using different Header

@GetMapping(path="/student",headers ="X-API-VERSION=1")
public Student version1Student() {
	return new Student("Bob Charlie");
}

@GetMapping(path="/student",headers ="X-API-VERSION=2")
public Student version2Student() {
	return new Student("Bob","Charlie");
}
```

### HATEOAS
How to perform subsequent actions?
- We will provide data and links to perform subsequent action with the help of hateoas.

#### Implmentation option:
1. We need to create bean with custom format and implement them.
2. Use standard Implementation - HAL or Spring HATEOAS
#### Import the class and its static methods:
```
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
```
#### Implementation of Hateoas
```
@GetMapping("/users")
public List<UserEntity> retrieveAllUsers(){
	return userServiceImpl.getAll();
}

@GetMapping("/user/{id}")
public EntityModel<UserEntity> hateoasGetByID(@PathVariable("id") Long id) {
	UserEntity user = userServiceImpl.getById(id).orElse(null);
	EntityModel<UserEntity> entityModel = EntityModel.of(user);
	WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
	entityModel.add(link.withRel("all-users"));
	return entityModel;
}
```

### Customizing the Response

1. Change fields name - @JsonProperty("user_name")
```
@Column(name="name")
@JsonProperty("user_name")
private String name;
```
2. Filtering 
   - Static Filtering  - @JsonIgnore, @JsonIgnoreProperties (It will be added in all the URI)
   ```
   @Column(name="password")
   @JsonIgnore
   private String password;
   
   or on class level also we can use
   
   @JsonIgnoreProperties({"password","dateofbirth"})
   class UserEntity{
   	// class implementation
   }
   ```
   - Dynamic Filtering - MappingJacksonValue, @JsonFilter with FilterProvider


### Spring Boot Actuator
- Monitoring API with Actuator
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
#### Link - http://localhost:8080/actuator

#### application.properties :
```
management.endpoints.web.exposure.include=*
```

#### Link
http://localhost:8080/actuator/beans
http://localhost:8080/actuator/env
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/http.server.request
http://localhost:8080/actuator/mappings


## HAL Explorer 
To exlore the api we can use HAL Explorer
```
<dependency>
	<groupId>org.springframework.data</groupId>
	<artifactId>spring-data-rest-hal-explorer</artifactId>
</dependency>
```

#### Link : http://localhost:8080/explorer/index.html#uri=/actuator
