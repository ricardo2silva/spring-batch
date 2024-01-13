package com.ricardo.estudospringbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PropsConfig {

    // removi as informações do application.properties e externalizei as informações, colocando-as no meu sistema e recuperando através deste Bean
    @Bean
    public PropertySourcesPlaceholderConfigurer config(){
        PropertySourcesPlaceholderConfigurer config = new PropertySourcesPlaceholderConfigurer();
        config.setLocation(new FileSystemResource("/etc/config/primeirojobspringbatch/application.properties"));
        return config;
    }
}
