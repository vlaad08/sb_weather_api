package com.vlad.weather_api.service;

import com.vlad.weather_api.client.OpenMeteoClient;
import com.vlad.weather_api.client.dto.GeocodeResponse;
import com.vlad.weather_api.client.dto.WeatherResponse;
import com.vlad.weather_api.dto.WeatherCurrentResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final OpenMeteoClient openMeteoClient;
    private final FavoriteService favoriteService;

    public WeatherService(OpenMeteoClient openMeteoClient, FavoriteService favoriteService) {
        this.openMeteoClient = openMeteoClient;
        this.favoriteService = favoriteService;
    }

    public WeatherCurrentResponse getCurrentForCity(String city){
        GeocodeResponse geo = openMeteoClient.getGeocodeCity(city);

        if(geo == null){
            throw new Error("City not found " + city);
        }

        GeocodeResponse.Result best = geo.results().getFirst();

        WeatherResponse weather = openMeteoClient.getCurrentWeather(best.latitude(), best.longitude());
        if (weather == null || weather.current() == null) {
            throw new RuntimeException("Weather provider returned no data");
        }

        return new WeatherCurrentResponse(
                best.name(),
                best.latitude(),
                best.longitude(),
                weather.current().temperature_2m(),
                weather.current().wind_speed_10m(),
                weather.current().weather_code()
        );
    }

    public List<WeatherCurrentResponse> getCurrentForFavs(){
        ArrayList<String> cities = favoriteService.getFavorites();
        ArrayList<WeatherCurrentResponse> responses = new ArrayList<>();

        for(String city: cities)
        {
            responses.add(this.getCurrentForCity(city));
        }

        return responses;
    }
}
