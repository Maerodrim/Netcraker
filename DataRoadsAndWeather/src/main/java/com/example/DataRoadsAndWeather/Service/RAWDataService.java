package com.example.DataRoadsAndWeather.Service;


import com.example.DataRoadsAndWeather.Interface.RoadsAndWeatherInterface;
import com.example.DataRoadsAndWeather.resources.Roads;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RAWDataService {


    @Autowired
    private RoadsAndWeatherInterface roadsAndWeatherInterface;

    @Transactional
    public void testEmployeesCrudRepository() {
        Optional<Roads> employeesOptional = roadsAndWeatherInterface.findById(127L);
        //....
    }
}
