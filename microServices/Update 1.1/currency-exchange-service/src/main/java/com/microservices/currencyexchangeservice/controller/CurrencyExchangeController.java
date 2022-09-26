package com.microservices.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.ExchangeValueRepo;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepo exchangeRepo;
	
	@GetMapping(path="/exchange/{from}/{to}")
	public ExchangeValue exchange(@PathVariable("from") String from,@PathVariable("to") String to) {
		ExchangeValue exchange =  exchangeRepo.findByFromAndTo(from, to);
		exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange;
	}
}
