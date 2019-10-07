package com.example.DataRoadsAndWeather.Service.Impl;

import com.example.DataRoadsAndWeather.Data.Weather;
import com.example.DataRoadsAndWeather.Dto.WeatherDto;
import com.example.DataRoadsAndWeather.Interface.WeatherRepo;
import com.example.DataRoadsAndWeather.Mappers.WeatherMapper;
import com.example.DataRoadsAndWeather.Service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor(onConstructor = @_(@Autowired))
@Service
@Primary
@Log4j2
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepo weatherRepo;

    public WeatherServiceImpl(WeatherRepo weatherRepository) {
        this.weatherRepo = weatherRepository;
    }

    @Override
    public List<Weather> getAllWeathers() throws NullPointerException {

        log.info("getAllUsers() method");

        return this.weatherRepo.findAll();
    }

    @Override
    public Weather getOnlyOneUser(Long id) throws NullPointerException {
        return this.weatherRepo.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public Weather signUp(WeatherDto weatherDto) throws IllegalArgumentException {

        Weather weather = WeatherMapper.INSTANCE.toWeather(weatherDto);

        // Добавляется новый пользователь, id must be null
        weather.setId(null);

        log.info("Сохранёна погода: " + weather.toString());

        return com.example.DataRoadsAndWeather.Service.Impl.weatherRepo.save(weather);
    }


    @Override
    public List<Weather> getAllExceptPrincipalWeather() {
        return this.weatherRepo.findAll();
    }
}
