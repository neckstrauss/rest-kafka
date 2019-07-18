package com.assertsolutions.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {
    
    @Value("${camel.springboot.name}")
    private String loggerName;
    
    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(loggerName);
    }

}
