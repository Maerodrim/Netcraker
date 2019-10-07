package com.example.DataRoadsAndWeather.Mappers;


import com.example.DataRoadsAndWeather.Data.Weather;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;
import org.mapstruct.InheritInverseConfiguration;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Component
@Mapper
public interface WeatherMapper {
    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    Weather toWeather(WeatherDto weatherDto);

    Collection<WeatherDto> toWeatherDTOs(Collection<Weather> weather);

    @InheritInverseConfiguration
    WeatherDto toWeatherDto(Weather weather);
}
