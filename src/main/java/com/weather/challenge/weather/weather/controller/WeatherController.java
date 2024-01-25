package com.weather.challenge.weather.weather.controller;

import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast5days")
    public ResponseEntity<WeatherResponseDto> getFiveDayForecast() {
        return ResponseEntity.ok(weatherService.getWeatherFiveDays());

    }

    @GetMapping("/current")
    public ResponseEntity<WeatherResponseDto> getCurrentWeather() {
        return ResponseEntity.ok(weatherService.getWeatherData());
    }
}
