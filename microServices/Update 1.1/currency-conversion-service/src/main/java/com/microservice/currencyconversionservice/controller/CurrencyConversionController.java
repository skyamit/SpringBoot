package com.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencyconversionservice.entity.CurrencyConversionEntity;
import com.microservice.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("conversion/{from}/{to}/{quantity}")
	public CurrencyConversionEntity convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionEntity> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/exchange/{from}/{to}", CurrencyConversionEntity.class,uriVariables);
		
		CurrencyConversionEntity response = responseEntity.getBody();
		return new CurrencyConversionEntity(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());	
	}
	
	@GetMapping("conversion-feign/{from}/{to}/{quantity}")
	public CurrencyConversionEntity convertCurrencyUsingFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {

		CurrencyConversionEntity response = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversionEntity(response.getId(),from,to,response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());	
	}
}
