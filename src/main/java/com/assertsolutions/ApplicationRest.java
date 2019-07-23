package com.assertsolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Asssert Solutions S.A.S
 */

@SpringBootApplication
@EnableAutoConfiguration
//@PropertySource("file:///${CONFIG_LOCATION}/kafka-producer.properties")

public class ApplicationRest {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRest.class, args);
    }

}
