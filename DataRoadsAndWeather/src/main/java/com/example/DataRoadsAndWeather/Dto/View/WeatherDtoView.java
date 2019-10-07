package com.example.DataRoadsAndWeather.Dto.View;

public class WeatherDtoView {

    public interface id {
    }

    public interface locationWeather {
    }

    public interface dateWeather {
    }

    public interface valueWind {
    }

    public interface valueRain {
    }

    public interface valueOvercast {
    }

    public interface valueTemp {
    }

    public interface Basic extends id, locationWeather, dateWeather{
    }

    public interface Full extends id, locationWeather, dateWeather,  valueWind, valueRain, valueOvercast, valueTemp {
    }
}

