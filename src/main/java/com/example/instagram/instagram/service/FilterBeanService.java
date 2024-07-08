package com.example.instagram.instagram.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class FilterBeanService {

    // fields is an array of field names you wish not to sned in your response
    // beanFilterName is value you give when you annotage your bean class
    // dataSet is the data you want to filter

    public static SimpleFilterProvider createFilterProvider() {
        return new SimpleFilterProvider();
    }

    public static SimpleFilterProvider addEntityFilter(SimpleFilterProvider filterProvider, String beanFilterName, String[] fields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(fields);
        filterProvider.addFilter(beanFilterName, filter);
        return filterProvider;
    }

    public static MappingJacksonValue getFilterdValue(SimpleFilterProvider filterProvider, Object dataSet, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", dataSet);
        map.put("message", message);
        MappingJacksonValue jacksonValue = new MappingJacksonValue(map);
        jacksonValue.setFilters(filterProvider);
        return jacksonValue;
    }
}