package com.example.DataRoadsAndWeather.Service;

import com.example.DataRoadsAndWeather.Data.Weather;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;

import java.util.List;

public interface WeatherService {

    List<Weather> getAllWeathers() throws NullPointerException;

    Weather getOnlyOneWeathers(Long id) throws NullPointerException;

    Weather signUp(WeatherDto weatherDto) throws IllegalArgumentException;

    List<Weather> getAllExceptPrincipalWeather();
}

