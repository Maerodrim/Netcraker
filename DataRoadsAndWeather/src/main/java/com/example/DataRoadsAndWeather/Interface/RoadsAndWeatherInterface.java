package com.example.DataRoadsAndWeather.Interface;

import com.example.DataRoadsAndWeather.resources.Roads;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadsAndWeatherInterface extends CrudRepository<Roads, Long> {

}
