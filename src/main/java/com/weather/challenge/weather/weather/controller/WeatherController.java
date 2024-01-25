package com.weather.challenge.weather.weather.controller;

import com.weather.challenge.weather.weather.model.WeatherResponseDto;
import com.weather.challenge.weather.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/current")
    public ResponseEntity<WeatherResponseDto> getCurrentWeather() {
        return ResponseEntity.ok(weatherService.getWeatherData());
    }

}
