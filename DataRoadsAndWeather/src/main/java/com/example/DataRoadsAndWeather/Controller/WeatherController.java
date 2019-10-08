package com.example.DataRoadsAndWeather.Controller;
/*
import com.example.DataRoadsAndWeather.Dto.Ext.SingleResponseObjectDtoExt;
import com.example.DataRoadsAndWeather.Dto.SingleResponseObjectDto;
import com.example.DataRoadsAndWeather.Dto.Status.StatusEnum;
import com.example.DataRoadsAndWeather.Dto.View.SingleResponseObjectDtoView;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;
import com.example.DataRoadsAndWeather.Mappers.WeatherMapper;
import com.example.DataRoadsAndWeather.Service.WeatherService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/DataWeather")
public class WeatherController {
    
    private final WeatherService weatherService;

    private final WeatherMapper weatherMapper = WeatherMapper.INSTANCE;


    @GetMapping("getWeatherJson")
    @JsonView(SingleResponseObjectDtoView.FullWithWeatherFull.class)
    public SingleResponseObjectDto getWeatherList() {


        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Вернул все записи",
                true,
                weatherMapper.toWeatherDTOs(this.weatherService.getAllWeathers())
        );

        return singleResponseObjectDto;
    }

    @PostMapping("addWeatherJson")
    @JsonView(SingleResponseObjectDtoView.FullWithWeatherFull.class)
    public SingleResponseObjectDto postWeather(@RequestBody WeatherDto weatherDto) {

        Optional<WeatherDto> optionalWeatherDto = Optional.of(this.weatherMapper.toWeatherDto(this.weatherService.signUp(weatherDto)));
        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Новая запись погоды",
                true,
                optionalWeatherDto.orElseThrow(NullPointerException::new)
        );

        return singleResponseObjectDto;

    }

}
*/