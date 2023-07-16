//package com.example.demophoto.converter;
//
//import com.example.demophoto.domain.Income;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.fasterxml.jackson.databind.util.Converter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//
//@Component
//@RequiredArgsConstructor
//public class IncomeConverter implements Converter<MultiValueMap<String, String>, Income> {
//    private final ObjectMapper objectMapper;
//
//    @Override
//    public Income convert(MultiValueMap<String, String> stringStringMultiValueMap) {
//        return objectMapper.convertValue(stringStringMultiValueMap, new TypeReference<>() {
//        });
//    }
//
//    @Override
//    public JavaType getInputType(TypeFactory typeFactory) {
//        return null;
//    }
//
//    @Override
//    public JavaType getOutputType(TypeFactory typeFactory) {
//        return null;
//    }
//}
