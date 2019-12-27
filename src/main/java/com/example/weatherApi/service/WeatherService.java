package com.example.weatherApi.service;

import com.example.weatherApi.WeatherAppProperties;
import com.example.weatherApi.dao.WeatherDao;
import com.example.weatherApi.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.List;

@Service
public class WeatherService {



    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

    private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

    private final RestTemplate restTemplate;

    private final String apiKey;

    private final WeatherDao weatherDao;


    @Autowired
    public WeatherService(RestTemplate restTemplate , WeatherDao weatherDao) {
        this.restTemplate = restTemplate;
//        this.apiKey = weatherAppProperties.getApi().getKey();
        this.apiKey = "87f64d7d5829492bb164f8b0012da8eb";
        this.weatherDao = weatherDao;
    }

    
    public String test(){
        return "STRING TEST ";
    }


    public List<Weather> getWeatherHistory(){

        return weatherDao.findAll();
    }

    @Cacheable("weather")
    public Weather getWeather(String country, String city) {

        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        Weather obj = invoke(url, Weather.class);
        weatherDao.save(obj);
        return obj;
    }



    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }



}
