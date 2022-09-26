package com.microservice.Limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.Limitsservice.configuration.Configurations;
import com.microservice.Limitsservice.model.Limits;

@RestController
public class LimitsController {
	
	@Autowired
	private Configurations configuration;
	
	@GetMapping(path="/limits")
	public Limits getLimit() {
		return new Limits(configuration.getMin(),configuration.getMax());
	}
}
