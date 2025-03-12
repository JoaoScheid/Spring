package com.springRomario.aulaSpring1.config;

import com.springRomario.aulaSpring1.model.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper config() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

//    @Bean
//    public Cliente cliente(){
//        return new Cliente();
//    }
}
