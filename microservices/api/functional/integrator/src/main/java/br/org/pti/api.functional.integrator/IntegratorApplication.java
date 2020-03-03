package br.org.pti.integrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 1.0.0
 * @since 2.0.0, 30/01/2020
 */
@EnableDiscoveryClient
@SpringBootApplication
public class IntegratorApplication {

    /**
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(IntegratorApplication.class, args);
    }
}
