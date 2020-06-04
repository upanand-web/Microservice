package com.upanand.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.upanand.microservice.currencyconversionservice.bean.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		//BigDecimal t = (BigDecimal.valueOf(65)) * (quantity) ;
		
		//problem 1 which resolve by feign
		Map<String, String> hm = new HashMap<String, String>();
		hm.put("from", from);
		hm.put("to", to);
		
		 ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				 CurrencyConversionBean.class, hm);
		 
		 CurrencyConversionBean responseBody = responseEntity.getBody();
		
		
		 return new CurrencyConversionBean(responseBody.getId(), from, to, responseBody.getConversionMultiple(),
				 quantity,quantity.multiply(responseBody.getConversionMultiple()) , responseBody.getPort());
		
	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, 
			@PathVariable BigDecimal quantity) {
		//BigDecimal t = (BigDecimal.valueOf(65)) * (quantity) ;
		
		//problem 1 which resolve by feign
		/*
		 * Map<String, String> hm = new HashMap<String, String>(); hm.put("from", from);
		 * hm.put("to", to);
		 * 
		 * ResponseEntity<CurrencyConversionBean> responseEntity = new
		 * RestTemplate().getForEntity(
		 * "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
		 * CurrencyConversionBean.class, hm);
		 */
		 
		 CurrencyConversionBean responseBody = proxy.retrieveExchangeValue(from, to);
		 
		 logger.info("response body -> {} ", responseBody);
		
		
		 return new CurrencyConversionBean(responseBody.getId(), from, to, responseBody.getConversionMultiple(),
				 quantity,quantity.multiply(responseBody.getConversionMultiple()) , responseBody.getPort());
		
	}

}
