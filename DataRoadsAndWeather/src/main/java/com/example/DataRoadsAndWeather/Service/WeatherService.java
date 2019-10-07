package com.example.DataRoadsAndWeather.Service;

import com.example.DataRoadsAndWeather.Data.Weather;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;

import java.util.List;

public interface WeatherService {

    List<Weather> getAllWeathers() throws NullPointerException;

    Weather getOnlyOneUser(Long id) throws NullPointerException;

    Weather signUp(WeatherDto userDto) throws IllegalArgumentException;

    List<Weather> getAllExceptPrincipalUser();
}

