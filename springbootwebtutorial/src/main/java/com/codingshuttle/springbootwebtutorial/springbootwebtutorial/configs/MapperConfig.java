package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.configs;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    ModelMapper ModelMapper()
    {
        return new ModelMapper();
    }

}
