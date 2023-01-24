package com.example.waa_first_demo.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
public class Util<E,V> {
    private static final ModelMapper staticModelMapper = new ModelMapper();

    public static  <E,V> V mapTo(E mapFrom, Class<V> mapTo) {
        return staticModelMapper.map(mapFrom,mapTo);
    }

    public static  <E,V> List<V> mapToListOf(List<E> mapFrom, Class<V> mapTo) {
        return mapFrom.stream().map(e -> staticModelMapper.map(e, mapTo)).collect(Collectors.toList());
    }

}
