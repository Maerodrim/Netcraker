package com.example.DataRoadsAndWeather.Dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class RoadsDto {
    @JsonView
    private String locationRoad;

    @JsonView
    private String dateRoad;

    @JsonView
    private String valueRoad;

}

