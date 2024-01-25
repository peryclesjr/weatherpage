package com.weather.challenge.weather.weather.model.dto;

import com.weather.challenge.weather.weather.model.details.CurrentWeather;
import com.weather.challenge.weather.weather.model.details.DailyWeather;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
public class WeatherResponseDto {
    private double lat;
    private double lon;
    private String timezone;
    private Optional<DailyWeather> daily;


    public Optional<DailyWeather> findColdestDay(List<DailyWeather> dailyWeathers) {
        if (dailyWeathers == null || dailyWeathers.isEmpty()) {
            return Optional.empty();
        }

        return dailyWeathers.stream()
                .sorted(Comparator
                        .comparingDouble((DailyWeather dw) -> dw.getTemp().getMin())
                        .thenComparingDouble(dw -> dw.getFeelsLike().getDay())
                        .thenComparingDouble(dw -> -dw.getWindSpeed())) // negative to sort descending
                        .findFirst();


    }


}
