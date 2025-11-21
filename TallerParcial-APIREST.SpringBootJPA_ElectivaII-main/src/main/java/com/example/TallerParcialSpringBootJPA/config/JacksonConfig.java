package com.example.TallerParcialSpringBootJPA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;

@Configuration
public class JacksonConfig {
    
    @Bean
    public Hibernate6Module hibernateModule() {
        Hibernate6Module hibernateModule = new Hibernate6Module();
        // No forzar la carga lazy, simplemente ignorar proxies lazy
        hibernateModule.disable(Hibernate6Module.Feature.USE_TRANSIENT_ANNOTATION);
        hibernateModule.disable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);
        hibernateModule.enable(Hibernate6Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        return hibernateModule;
    }
}