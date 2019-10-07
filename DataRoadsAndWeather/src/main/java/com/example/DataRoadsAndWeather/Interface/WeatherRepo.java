package com.example.DataRoadsAndWeather.Interface;

import com.example.DataRoadsAndWeather.Data.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRepo extends CrudRepository<Weather,Long> {
    List<Weather> findByLocationWeather(String locationWeather);
}
