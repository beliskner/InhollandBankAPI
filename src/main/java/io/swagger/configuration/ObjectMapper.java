package io.swagger.configuration;

import io.swagger.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@Configuration
@EnableSpringConfigured
public class ObjectMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}