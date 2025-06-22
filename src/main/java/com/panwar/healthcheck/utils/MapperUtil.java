package com.panwar.healthcheck.utils;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapperUtil {
    private static final ModelMapper modelmapper = new ModelMapper();

    public static <S, D> D map(S source, Class<D> destinationClass) {
        return modelmapper.map(source, destinationClass);
    }

}
