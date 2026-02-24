package com.vlad.weather_api.client.dto;

import java.util.List;

public record GeocodeResponse(
        List<Result> results
) {
    public record Result(
            String name,
            double latitude,
            double longitude,
            String country
    ) {}
}
