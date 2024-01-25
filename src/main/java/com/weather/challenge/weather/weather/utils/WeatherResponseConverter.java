package com.weather.challenge.weather.weather.utils;

import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.WeatherResponseDto;

public class WeatherResponseConverter {

    public static WeatherResponseDto toDto(WeatherResponse weatherResponse) {
        if (weatherResponse == null) {
            return null;
        }

        WeatherResponseDto dto = new WeatherResponseDto();
        dto.setLat(weatherResponse.getLat());
        dto.setLon(weatherResponse.getLon());
        dto.setTimezone(weatherResponse.getTimezone());
        dto.setCurrent(weatherResponse.getCurrent());
        return dto;
    }
}