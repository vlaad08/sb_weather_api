package com.vlad.weather_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class WeatherApiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
						.ignoreIfMissing()
								.load();

		System.setProperty("OPENAI_API_KEY", dotenv.get("OPENAI_API_KEY"));

		SpringApplication.run(WeatherApiApplication.class, args);
	}

}
