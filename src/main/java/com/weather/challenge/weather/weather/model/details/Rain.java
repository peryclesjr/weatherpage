package com.weather.challenge.weather.weather.model.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Rain {
    @JsonProperty("1h")
    private double oneHour;
}
