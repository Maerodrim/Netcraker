package com.example.DataRoadsAndWeather.Dto.View;

public class RoadsDtoView {

    public interface id {
    }

    public interface locationRoads {
    }

    public interface dateRoads {
    }

    public interface valueRoads {
    }


    public interface Basic extends id, locationRoads, dateRoads{
    }

    public interface Full extends id, locationRoads, dateRoads,  valueRoads{
    }
}

