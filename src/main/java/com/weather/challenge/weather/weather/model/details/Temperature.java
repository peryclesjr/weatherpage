package com.weather.challenge.weather.weather.model.details;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Temperature {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;
}
