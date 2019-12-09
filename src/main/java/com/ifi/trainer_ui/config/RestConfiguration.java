package com.ifi.trainer_ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String user;
    @Value("${trainer.service.password}")
    private String password;
    @Bean
    RestTemplate trainerApiRestTemplate(){
        RestTemplate restTemplate=new RestTemplate();
        ClientHttpRequestInterceptor interceptor=new BasicAuthenticationInterceptor("user","a63d7c96-1a5d-11ea-978f-2e728ce88125");
        List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();
        interceptors.add(interceptor);
        restTemplate.setInterceptors(interceptors);
    return restTemplate;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    }

