package com.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.currencyexchangeservice.model.ExchangeValue;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from,String to);
}
