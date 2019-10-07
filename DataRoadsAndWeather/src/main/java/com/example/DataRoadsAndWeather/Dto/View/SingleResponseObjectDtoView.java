package com.example.DataRoadsAndWeather.Dto.View;

public class SingleResponseObjectDtoView {

    public interface StatusCode {
    }

    public interface Message {
    }

    public interface Success {
    }

    public interface DataOrException {
    }

    public interface Full extends StatusCode, Message, Success, DataOrException {
    }

    public interface FullWithWeatherFull extends Full, WeatherDtoView.Full {
    }
    public interface FullWithRoadsFull extends Full, RoadsDtoView.Full {
    }
}
