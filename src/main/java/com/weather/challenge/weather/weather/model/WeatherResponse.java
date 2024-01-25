package com.weather.challenge.weather.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.challenge.weather.weather.model.details.CurrentWeather;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private double lat;
    private double lon;
    private String timezone;
    @JsonProperty("timezone_offset")
    private int timezoneOffset;
    private CurrentWeather current;
//    private List<MinutelyWeather> minutely;
//    private List<HourlyWeather> hourly;
//    private List<DailyWeather> daily;
//    private List<WeatherAlert> alerts;


}