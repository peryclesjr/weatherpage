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

    @GetMapping("/coldest-day-next-5-days")
    public ResponseEntity<WeatherResponseDto> getColdestDayNextFive(@RequestParam(value = "lat", defaultValue = "49.837012") String lat,
                                                                    @RequestParam(value = "lon", defaultValue = "-97.170069")  String lon) {
        Optional<WeatherResponseDto> optional = weatherService.getWeatherFiveDays(lat, lon);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

}
