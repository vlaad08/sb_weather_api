package com.vlad.weather_api.controller;

import com.vlad.weather_api.dto.AddFavoriteRequest;
import com.vlad.weather_api.dto.AddFavoriteResponse;
import com.vlad.weather_api.service.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class FavoritesController {

    private final FavoriteService favoriteService;

    public FavoritesController(FavoriteService favoriteService){
        this.favoriteService = favoriteService;
    }

    @PostMapping("/favorites")
    public ResponseEntity<AddFavoriteResponse> addFavorite(
            @RequestBody @Valid AddFavoriteRequest request) {

        favoriteService.addCity(request.city());

        AddFavoriteResponse response = new AddFavoriteResponse(
                "City added successfully",
                201
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
