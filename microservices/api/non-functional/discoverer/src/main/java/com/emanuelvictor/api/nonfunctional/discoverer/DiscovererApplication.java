package com.emanuelvictor.api.nonfunctional.discoverer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author Emanuel Victor
 *
 * @version 1.0.0
 * @since 1.0.0, 20/07/2017
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscovererApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DiscovererApplication.class, args);
    }
}
