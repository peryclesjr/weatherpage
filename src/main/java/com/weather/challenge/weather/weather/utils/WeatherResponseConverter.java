package com.weather.challenge.weather.weather.utils;

import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

        Instant instant = Instant.ofEpochSecond(dto.getDaily().get().getDt());
        dto.setDate(LocalDate.from(instant.atZone(ZoneId.systemDefault()).toLocalDateTime()));

        dto.setMinTempCelsius(dto.getDaily().get().getTemp().getMin());
        dto.setMinTempFahrenheit(convertCelsiusToFahrenheit(dto.getDaily().get().getTemp().getMin()));

        return dto;
    }

    public static double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }


}