package com.kemisshop.catalogservice.config;

/*
    wontgn created on 11/2/20 inside the package - com.farmshop.catalogservice.config
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class CatalogServiceConfig {

    @Bean
    public ExecutorService getExecutorService() {
        return Executors.newFixedThreadPool(8);
    }

}
