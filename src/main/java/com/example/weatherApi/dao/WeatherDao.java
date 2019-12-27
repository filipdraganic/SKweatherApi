package com.example.weatherApi.dao;


import com.example.weatherApi.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDao extends MongoRepository<Weather, String> {

    Weather getAllByCity(String City);

}
