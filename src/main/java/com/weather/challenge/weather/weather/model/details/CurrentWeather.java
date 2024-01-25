package com.weather.challenge.weather.weather.model.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weather.challenge.weather.weather.model.details.Rain;
import com.weather.challenge.weather.weather.model.details.Weather;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CurrentWeather {
    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    private int pressure;
    private int humidity;
    @JsonProperty("dew_point")
    private double dewPoint;
    private double uvi;
    private int clouds;
    private int visibility;
    @JsonProperty("wind_speed")
    private double windSpeed;
    @JsonProperty("wind_deg")
    private int windDeg;
    private List<Weather> weather;
    private Rain rain;

}