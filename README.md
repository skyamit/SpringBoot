# RestApi Basics :

## Annotations :

1. @SpringBootApplication 
2. @RestController
3. @AutoWired

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
return new ResponseEntity(Object,HttpStatus.INTERNAL_SERVER_ERROR);

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
public class ErrorDetails{
   private LocalDate timestamp;
   private String message;
   private String details;

   // generate getter, setters, and constructor
 }
 
 @ControllerAdvice
 public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler{
   
   @ExceptionHandler(Exception.class)
   public final ResponseEntity<Object> handleAllException(Exception ex,WebRequest request){
      ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),ex.getMessage(),request.getDescription(false));
      return new ResponseEntity(errorDetails,Https.INTERNAL_SERVER_ERROR);
   }
 }
      
