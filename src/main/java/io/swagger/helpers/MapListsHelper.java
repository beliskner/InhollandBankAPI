package io.swagger.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@Configurable
public class MapListsHelper {

    //constructor is nodig om te kunnen autowiren...

    public MapListsHelper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public static ModelMapper modelMapper;

    public static  <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }


}
