package com.weather.challenge.weather.weather.model.details;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MinutelyWeather {
    private long dt;
    private double precipitation;

}
