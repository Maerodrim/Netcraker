package com.example.DataRoadsAndWeather.Mappers;

import com.example.DataRoadsAndWeather.Data.Roads;
import com.example.DataRoadsAndWeather.Dto.RoadsDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Mapper
public interface RoadsMapper {
    RoadsMapper INSTANCE = Mappers.getMapper(RoadsMapper.class);

    Roads toRoads(RoadsDto roadsDto);

    Collection<RoadsDto> toRoadsDTOs(Collection<Roads> roads);

    @InheritInverseConfiguration
    RoadsDto toRoadsDto(Roads roads);
}