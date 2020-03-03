package br.org.pti.api.functional.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthorizationManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationManagerApplication.class, args);
    }

}
