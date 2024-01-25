package com.weather.challenge.weather.weather.controller;

import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/forecast5days")
    public ResponseEntity<WeatherResponseDto> getFiveDayForecast() {
        Optional<WeatherResponseDto> optional = weatherService.getWeatherFiveDays();
        if (    optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
