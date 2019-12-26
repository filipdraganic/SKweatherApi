package com.example.weatherApi.service;

import com.example.weatherApi.WeatherAppProperties;
import com.example.weatherApi.model.Weather;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Service
public class WeatherService {



    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

    private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

    private final RestTemplate restTemplate;

    private final String apiKey;




    public WeatherService(RestTemplate restTemplate, WeatherAppProperties properties) {
        this.restTemplate = restTemplate;
        this.apiKey = properties.getApi().getKey();
    }

    
    public String test(){
        return "STRING TEST ";
    }




    @Cacheable("weather")
    public Weather getWeather(String country, String city) {

        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        return invoke(url, Weather.class);
    }



    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }



}
