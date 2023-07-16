//package com.example.demophoto.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@RequiredArgsConstructor
//public class CustomWebConfigurer implements WebMvcConfigurer {
//
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter((Converter<?, ?>) new IncomeConverter(objectMapper));
//    }
//
//}
