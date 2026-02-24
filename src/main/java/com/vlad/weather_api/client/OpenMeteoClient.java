package com.vlad.weather_api.client;

import com.vlad.weather_api.client.dto.GeocodeResponse;
import com.vlad.weather_api.client.dto.WeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class OpenMeteoClient {
    private final RestClient geocodingClient;
    private final RestClient weatherClient;

    public OpenMeteoClient(){
        this.geocodingClient = RestClient.builder().baseUrl("https://geocoding-api.open-meteo.com").build();
        this.weatherClient = RestClient.builder().baseUrl("https://api.open-meteo.com").build();
    }

    public GeocodeResponse getGeocodeCity(String city){
        URI uri = UriComponentsBuilder.fromPath("/v1/search")
                .queryParam("name", city)
                .queryParam("count", 1)
                .build(true)
                .toUri();

        return geocodingClient.get()
                .uri(uri)
                .retrieve()
                .body(GeocodeResponse.class);
    }

    public WeatherResponse getCurrentWeather(double lat, double lon) {
        URI uri = UriComponentsBuilder.fromPath("/v1/forecast")
                .queryParam("latitude", lat)
                .queryParam("longitude", lon)
                .queryParam("current", "temperature_2m,wind_speed_10m,weather_code")
                .queryParam("wind_speed_unit", "kmh")
                .build(true)
                .toUri();

        return weatherClient.get()
                .uri(uri)
                .retrieve()
                .body(WeatherResponse.class);
    }
}

