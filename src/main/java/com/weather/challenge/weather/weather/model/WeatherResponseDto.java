package com.weather.challenge.weather.weather.model;

import com.weather.challenge.weather.weather.model.details.CurrentWeather;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WeatherResponseDto {
    private double lat;
    private double lon;
    private String timezone;
    private CurrentWeather current;
}
