package com.weather.challenge.weather.weather.utils;

import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;

public class WeatherResponseConverter {

    public static WeatherResponseDto toDto(WeatherResponse weatherResponse) {
        if (weatherResponse == null) {
            return null;
        }

        WeatherResponseDto dto = new WeatherResponseDto();
        dto.setLat(weatherResponse.getLat());
        dto.setLon(weatherResponse.getLon());
        dto.setTimezone(weatherResponse.getTimezone());
        dto.setDaily(dto.findColdestDay(weatherResponse.getDaily()));

        return dto;
    }


}