package com.upanand.microservice.springcloudconfigserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/demo")
	public int  getIntValue() {
		int max = 9000;
		return max;
	}

}
