package com.vlad.weather_api.dto;

import jakarta.validation.constraints.NotBlank;

public record AddFavoriteRequest(
        @NotBlank String city
) {
}
