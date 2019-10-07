package com.example.DataRoadsAndWeather;

import com.example.DataRoadsAndWeather.Dto.Ext.SingleResponseObjectDtoExt;
import com.example.DataRoadsAndWeather.Dto.RoadsDto;
import com.example.DataRoadsAndWeather.Dto.SingleResponseObjectDto;
import com.example.DataRoadsAndWeather.Dto.Status.StatusEnum;
import com.example.DataRoadsAndWeather.Dto.View.SingleResponseObjectDtoView;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;
import com.example.DataRoadsAndWeather.Interface.RoadsRepo;
import com.example.DataRoadsAndWeather.Interface.WeatherRepo;
import com.example.DataRoadsAndWeather.Mappers.RoadsMapper;
import com.example.DataRoadsAndWeather.Mappers.WeatherMapper;
import com.example.DataRoadsAndWeather.Service.RoadsService;
import com.example.DataRoadsAndWeather.Service.WeatherService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/DataRAW")
public class MainController {

    private RoadsRepo roadsRepo;

    private WeatherRepo weatherRepo;

    private final WeatherService weatherService;

    private final RoadsService roadsService;

    private final RoadsMapper roadsMapper = RoadsMapper.INSTANCE;

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
        weatherRepo.save(weatherMapper.toWeather(weatherDto));
        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Новая запись погоды",
                true,
                optionalWeatherDto.orElseThrow(NullPointerException::new)
        );

        return singleResponseObjectDto;

    }
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
        roadsRepo.save(roadsMapper.toRoads(roadsDto));
        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Новая запись погоды",
                true,
                optionalRoadsDto.orElseThrow(NullPointerException::new)
        );

        return singleResponseObjectDto;

    }

}