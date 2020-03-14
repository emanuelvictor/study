package br.org.pti.api.nonfunctional.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 */
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class ProxyApplication {

    /**
     * @param args String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
}