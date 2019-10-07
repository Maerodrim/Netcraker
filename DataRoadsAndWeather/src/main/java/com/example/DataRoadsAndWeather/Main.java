package com.example.DataRoadsAndWeather;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
public class Main {
    /*
    @Autowired
    private RoadsRepo roadsRepo;
    @Autowired
    private WeatherRepo weatherRepo;
    @Autowired
    private final RoadsMapper roadsMapper = RoadsMapper.INSTANCE;
    @Autowired
    private final WeatherMapper weatherMapper = WeatherMapper.INSTANCE;
    @Autowired
    private final WeatherService weatherService;


    @GetMapping("getWeatherJson")
    @JsonView(SingleResponseObjectDtoView.FullWithWeatherFull.class)
    public SingleResponseObjectDto getUsersList() {


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
    }*/
}