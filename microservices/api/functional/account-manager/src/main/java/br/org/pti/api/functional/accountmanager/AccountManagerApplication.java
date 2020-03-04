package br.org.pti.api.functional.accountmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountManagerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AccountManagerApplication.class, args);
    }

}
