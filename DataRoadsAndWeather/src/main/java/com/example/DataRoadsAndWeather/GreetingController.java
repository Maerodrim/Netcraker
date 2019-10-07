package com.example.DataRoadsAndWeather;

import com.example.DataRoadsAndWeather.Data.Message;
import com.example.DataRoadsAndWeather.Data.Roads;
import com.example.DataRoadsAndWeather.Data.Weather;
import com.example.DataRoadsAndWeather.Interface.MessageRepo;
import com.example.DataRoadsAndWeather.Interface.RoadsRepo;
import com.example.DataRoadsAndWeather.Interface.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private RoadsRepo roadsRepo;
    @Autowired
    private WeatherRepo weatherRepo;
    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Roads> roads = roadsRepo.findAll();

        model.put("roads", roads);
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        Iterable<Weather> weathers = weatherRepo.findAll();

        model.put("weathers", weathers);

        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("addRoads")
    public String addRoads(@RequestParam String locationRoad, @RequestParam String dateRoad,@RequestParam String valueRoad,
                           Map<String, Object> model) {
        Roads road = new Roads(locationRoad, dateRoad,Double.parseDouble(valueRoad));

        roadsRepo.save(road);

        Iterable<Roads> roads = roadsRepo.findAll();

        model.put("roads", roads);

        return "main";
    }
    @PostMapping("addWeather")
    public String addWeather(@RequestParam String locationWeather, @RequestParam String dateWeather,@RequestParam String valueWind,
                             @RequestParam String valueRain, @RequestParam String valueOvercast,@RequestParam String valueTemp,
                             Map<String, Object> model) {
        Weather weather = new Weather(locationWeather, dateWeather,Double.parseDouble(valueWind),
                Double.parseDouble(valueRain), Double.parseDouble(valueOvercast),
                Double.parseDouble(valueTemp));

        weatherRepo.save(weather);

        Iterable<Weather> weathers = weatherRepo.findAll();

        model.put("weathers", weathers);

        return "main";
    }
    @PostMapping(path = "/all")
    public String delete() {
        roadsRepo.deleteAll();
        messageRepo.deleteAll();
        weatherRepo.deleteAll();
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}
