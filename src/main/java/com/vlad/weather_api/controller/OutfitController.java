package com.vlad.weather_api.controller;

import com.vlad.weather_api.service.OutfitService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutfitController {

    private final OutfitService outfitService;

    public OutfitController(OutfitService outfitService){
        this.outfitService = outfitService;
    }

    @GetMapping("/ai/outfit")
    public String outfit(@RequestParam @NotBlank String city){
        return this.outfitService.suggestOutfits(city);
    }
}
