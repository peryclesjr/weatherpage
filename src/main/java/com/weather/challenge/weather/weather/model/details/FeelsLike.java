package com.weather.challenge.weather.weather.model.details;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class FeelsLike {
    private double day;
    private double night;
    private double eve;
    private double morn;
}
