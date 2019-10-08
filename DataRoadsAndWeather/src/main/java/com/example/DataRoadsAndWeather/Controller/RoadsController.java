package com.example.DataRoadsAndWeather.Controller;
/*
import com.example.DataRoadsAndWeather.Dto.Ext.SingleResponseObjectDtoExt;
import com.example.DataRoadsAndWeather.Dto.RoadsDto;
import com.example.DataRoadsAndWeather.Dto.SingleResponseObjectDto;
import com.example.DataRoadsAndWeather.Dto.Status.StatusEnum;
import com.example.DataRoadsAndWeather.Dto.View.SingleResponseObjectDtoView;
import com.example.DataRoadsAndWeather.Mappers.RoadsMapper;
import com.example.DataRoadsAndWeather.Service.RoadsService;
import lombok.RequiredArgsConstructor;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/DataRoads")
public class RoadsController {

    private final RoadsService roadsService;

    private final RoadsMapper roadsMapper = RoadsMapper.INSTANCE;


    @GetMapping("getRoadsJson")
    @JsonView(SingleResponseObjectDtoView.FullWithWeatherFull.class)
    public SingleResponseObjectDto getRoadsList() {


        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Вернул все записи",
                true,
                roadsMapper.toRoadsDTOs(this.roadsService.getAllRoads())
        );

        return singleResponseObjectDto;
    }

    @PostMapping("addRoadsson")
    @JsonView(SingleResponseObjectDtoView.FullWithWeatherFull.class)
    public SingleResponseObjectDto postRoads(@RequestBody RoadsDto roadsDto) {

        Optional<RoadsDto> optionalRoadsDto = Optional.of(this.roadsMapper.toRoadsDto(this.roadsService.signUp(roadsDto)));
        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Новая запись погоды",
                true,
                optionalRoadsDto.orElseThrow(NullPointerException::new)
        );

        return singleResponseObjectDto;

    }

}*/
