package com.upanand.microservice.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.upanand.microservice.currencyconversionservice.bean.CurrencyConversionBean;

/**
 * This is the feignClient which talks to external microservice
 * here we need to pass that service name which need to consume and their URL .
 * 
 * We are using ribbon here which help to balance the load between the different instance.
 * 
 * Once we are using feign client no need to give the URL in feign client that URL we have to fetch from 
 * application.properties.
 * @author upanand
 *
 */
//@FeignClient(name = "currency-exchange-service",url = "localhost:8000")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public  CurrencyConversionBean retrieveExchangeValue(@PathVariable String from , @PathVariable String to);
}
