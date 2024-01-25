package com.weather.challenge.weather.weather.model.details;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

}