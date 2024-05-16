package com.sinam7.booksage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    public WebClient KyoboApiClient() {
        return WebClient.create("https://product.kyobobook.co.kr");
    }

//    @Bean
//    public WebClient InterparkApiClient() {
//        return WebClient.create("https://book.interpark.com");
//    }
}
