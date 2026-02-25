package com.vlad.weather_api.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class FavoriteService {
    private static final String PATH = "data/favorites.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void addCity(String city){
        try{
            Map<Integer, String> favs = readFromFile();

            int id = favs.isEmpty() ? 1 : favs.keySet().stream().max(Integer::compareTo).get() + 1;

            favs.put(id, city);

            writeToFile(favs);
        }catch (Exception e)
        {
            throw new RuntimeException("Failed to save city " + city);
        }
    }

    public ArrayList<String> getFavorites(){
        try{
            Map<Integer, String> favs = readFromFile();

            return new ArrayList<>(favs.values());
        }
        catch (Exception e){
            throw new RuntimeException("Failed to read from file");
        }
    }

    private Map<Integer, String> readFromFile() throws Exception {
        try{
            File file = new File(PATH);

            return objectMapper.readValue(
                    file,
                    new TypeReference<LinkedHashMap<Integer, String>>() {}
            );
        }
        catch (Exception e){
            throw new Exception("File could not be read");
        }
    }

    private void writeToFile(Map<Integer, String> favorites) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(PATH), favorites);
    }
}
