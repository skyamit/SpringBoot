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

## Path Parameters
GetMapping("/somepath/{id}")
public SomeClassName function(@PathVariable("id") long id)

## Request Methods 
1. GetMapping - Retrieve Details of resources
2. PostMapping - create a new resources
3. PutMapping - update a exisiting resources
4. PatchMapping - update a part of resources
5. DeleteMapping - Delete a resources


