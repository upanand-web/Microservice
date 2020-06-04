package com.upanand.microservice.limitsservice;


import com.upanand.microservice.limitsservice.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retreiveLimitsFromConfiguration(){
        return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());

    }
}
