package br.org.pti.api.nonfunctional.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;

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

    /**
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            /**
             *
             * @param registry CorsRegistry
             */
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8082")
//                        .allowCredentials(true)
                        .allowedHeaders("access-control-allow-origin", "x-requested-with", "authorization", "Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization")
                        .allowedMethods("OPTIONS", "DELETE", "GET", "POST", "PATCH", "PUT");

//                configuration.setAllowedOrigins(Collections.singletonList("*"));
//                configuration.setAllowCredentials(true);
//                configuration.setAllowedHeaders(Arrays.asList("access-control-allow-origin", "x-requested-with", "authorization", "Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
//                configuration.setAllowedMethods(Arrays.asList("OPTIONS", "DELETE", "GET", "POST", "PATCH", "PUT"));
            }
        };
    }
}
