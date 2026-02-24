package com.vlad.weather_api.controller;

import com.vlad.weather_api.dto.WeatherCurrentResponse;
import com.vlad.weather_api.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/current")
    public WeatherCurrentResponse current(@RequestParam @NotBlank String city)
    {
        return weatherService.getCurrentForCity(city);
    }

    @GetMapping("/weather/favorites/current")
    public List<WeatherCurrentResponse> currentFavorites(){
        return weatherService.getCurrentForFavs();
    }
}
