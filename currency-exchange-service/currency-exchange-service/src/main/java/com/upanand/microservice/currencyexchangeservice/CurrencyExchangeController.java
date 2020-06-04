package com.upanand.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from , @PathVariable String to) {
		
		ExchangeValue exchangeValue = null;
		
		if(from != null && from.equalsIgnoreCase("USD")) {
		exchangeValue = new ExchangeValue(1001L,from,to , BigDecimal.valueOf(65)); // hard coded implementation
		}
		else if(from != null && from.equalsIgnoreCase("EURO")) {
			exchangeValue = new ExchangeValue(1002L,from,to , BigDecimal.valueOf(75));
		}else if(from != null && from.equalsIgnoreCase("AUD")) {
			exchangeValue = new ExchangeValue(1003L,from,to , BigDecimal.valueOf(25));
		}else {
		
		exchangeValue = new ExchangeValue(1000L,from,to , BigDecimal.valueOf(1));
		}
		//ExchangeValue exchangeValue =  repository.findByFromAndTo(from, to); // For fetching the data from h2 database !!
		
		
		exchangeValue.port = Integer.parseInt(environment.getProperty("local.server.port"));
		
		logger.info("exchange value -> {} ",exchangeValue.getId());
		
		
		
		return exchangeValue;
	}

}
