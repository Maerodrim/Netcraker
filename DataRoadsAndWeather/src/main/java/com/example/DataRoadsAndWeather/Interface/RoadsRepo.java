package com.example.DataRoadsAndWeather.Interface;

import com.example.DataRoadsAndWeather.Data.Roads;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoadsRepo extends CrudRepository<Roads,Long> {
    List<Roads> findByLocationRoad(String locationRoad);
}
