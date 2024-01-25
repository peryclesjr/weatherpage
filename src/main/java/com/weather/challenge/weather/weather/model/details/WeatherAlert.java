package com.weather.challenge.weather.weather.model.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WeatherAlert {
    @JsonProperty("sender_name")
    private String senderName;
    private String event;
    private long start;
    private long end;
    private String description;
    private List<String> tags;
}
