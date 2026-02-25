package com.vlad.weather_api.service;

import com.vlad.weather_api.dto.WeatherCurrentResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OutfitService {
    private final ChatModel chatModel;
    private final WeatherService weatherService;

    public OutfitService(ChatModel chatModel, WeatherService weatherService){
        this.chatModel = chatModel;
        this.weatherService = weatherService;
    }


    public String suggestOutfits(String city){
        WeatherCurrentResponse weatherResponse = weatherService.getCurrentForCity(city);

        String template = """
                You suggest outfits adequate for the current weather of a given city. The response needs to be short and practical, without saying non-sense.
                
                Weather:
                City: {city}
                TemperatureC: {temp}
                WindKph: {wind}
                Weather code: {code}
                
                Output this format:
                <p> Day: <outfit> </p>
                <p> Night: <outfit> </p>
                <p> Outside sports/activities: <outfit> </p>
                
                Rules:
                - Mention shoes
                - Mention if a cap or a hoodie is enough
                - Mention if protection/outer layers are necessary if TemperatureC OR WindKph are impactful.
                """;

        Prompt prompt = new PromptTemplate(template).create(Map.of(
                "city", weatherResponse.city(),
                "temp", weatherResponse.temperatureC(),
                "wind", weatherResponse.windSpeedKph(),
                "code", weatherResponse.weatherCode()
        ));

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
