package com.panwar.healthcheck.utils;

import org.modelmapper.ModelMapper;

// TODO: Will use this class for mapping in future
public class MapperUtil {
    private static final ModelMapper modelmapper = new ModelMapper();

    public static <S, D> D map(S source, Class<D> destinationClass) {
        return modelmapper.map(source, destinationClass);
    }

}
