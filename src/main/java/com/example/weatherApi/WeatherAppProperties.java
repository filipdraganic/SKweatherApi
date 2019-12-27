package com.example.weatherApi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@ConfigurationProperties("app.weatherr")
@Configuration
public class WeatherAppProperties {


    private final Api api = new Api();

    /**
     * Comma-separated list of locations to display. Each entry should have the
     * form "Country/City".
     */
    private List<String> locations = Arrays.asList("UK/London", "Spain/Barcelona");

    public Api getApi() {
        return this.api;
    }

    public List<String> getLocations() {
        return this.locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public static class Api {

        /**
         * API key of the OpenWeatherMap service.
         */
        @NotNull
        private String key = "87f64d7d5829492bb164f8b0012da8eb";

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

}
