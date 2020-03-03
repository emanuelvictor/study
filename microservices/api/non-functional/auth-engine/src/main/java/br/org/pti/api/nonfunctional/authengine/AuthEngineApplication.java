package br.org.pti.api.nonfunctional.authengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthEngineApplication.class, args);
    }

}
