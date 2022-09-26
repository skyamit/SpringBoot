package com.microservice.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.currencyconversionservice.entity.CurrencyConversionEntity;

//@FeignClient(name="currency-exchange-service",url="localhost:8000")
@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping(path="/exchange/{from}/{to}")
	public CurrencyConversionEntity retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
