package com.vlad.weather_api.client.dto;

public record WeatherResponse(
    Current current
) {
    public record Current(
            double temperature_2m,
            double wind_speed_10m,
            int weather_code
    ){}
}
