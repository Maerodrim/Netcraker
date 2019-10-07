package com.example.DataRoadsAndWeather.Service;

import com.example.DataRoadsAndWeather.Data.Roads;
import com.example.DataRoadsAndWeather.Dto.RoadsDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoadsService{
        List<Roads> getAllRoads() throws NullPointerException;

        Roads getOnlyOneRoads(Long id) throws NullPointerException;

        Roads signUp(RoadsDto roadsDto) throws IllegalArgumentException;

        List<Roads> getAllExceptPrincipalRoads();
}
