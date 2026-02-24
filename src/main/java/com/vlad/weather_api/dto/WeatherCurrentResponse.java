package com.vlad.weather_api.dto;

public record WeatherCurrentResponse(
        String city,
        double latitude,
        double longitude,
        double temperatureC,
        double windSpeedKph,
        int weatherCode
) {
}
