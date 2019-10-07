package com.example.DataRoadsAndWeather.Dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class WeatherDto {
    @JsonView
    private String locationWeather;

    @JsonView
    private String dateWeather;

    @JsonView
    private Double valueWind;

    @JsonView
    private Double valueRain;

    @JsonView
    private Double valueOvercast;

    @JsonView
    private Double valueTemp;
}
