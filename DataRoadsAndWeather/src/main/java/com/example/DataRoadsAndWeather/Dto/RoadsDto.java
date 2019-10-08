package com.example.DataRoadsAndWeather.Dto;

import com.example.DataRoadsAndWeather.Dto.View.RoadsDtoView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class RoadsDto {

    @JsonView(RoadsDtoView.id.class)
    private String id;

    @JsonView(RoadsDtoView.locationRoads.class)
    private String locationRoad;

    @JsonView(RoadsDtoView.dateRoads.class)
    private String dateRoad;

    @JsonView(RoadsDtoView.valueRoads.class)
    private String valueRoad;

}

