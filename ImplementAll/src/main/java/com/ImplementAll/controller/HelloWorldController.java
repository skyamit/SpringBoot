package com.ImplementAll.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ImplementAll.model.HelloWorld;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("/hello-world-I18N")
	public String helloWordInternationalisation() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message",null,"Default Message", locale);
	}
	
	@GetMapping("/hello-world")
	public String helloWord() {
		return "Hello World";
	}
	
	
	@GetMapping("/hello-world-bean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Amit Kumar is trying something new");
	}
	
	@GetMapping("/helloWorld/{name}")
	public HelloWorld getname(@PathVariable("name") String name) {
		return new HelloWorld("Hello World "+name);
	}
	
	
}
