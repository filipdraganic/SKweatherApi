package com.example.weatherApi.api;


import com.example.weatherApi.model.Weather;
import com.example.weatherApi.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/weather")
@RestController
public class WeatherController {


    private final WeatherService weatherService;


    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping
    public Weather getWeather(@RequestParam(name="country") String country, @RequestParam(name="city") String city){
        System.out.println(country + " " + city);
        return weatherService.getWeather(country, city);
    }

    @GetMapping("/getHistory")
    public List<Weather> getWeatherHistory(){
        return weatherService.getWeatherHistory();
    }

    @GetMapping("/test")
    public String test(){
        return weatherService.test();
    }

}
