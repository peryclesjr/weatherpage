package com.weather.challenge.weather.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.utils.ApiConnectionWeather;
import com.weather.challenge.weather.weather.utils.WeatherResponseConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import java.io.IOException;
import java.time.Instant;
import java.util.Optional;


@Service
public class WeatherService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherService() {
    }

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.lat}")
    private String latitude;

    @Value("${weather.lon}")
    private String longitude;

    @Value("${weather.base.url}")
    private String urlBase;

    @Value("${weather.forecastfivedays.url}")
    private String urlForecastFiveDays;



    //converts the json to a WeatherResponse object
    public WeatherResponse toWeatherResponse(String json) throws IOException {
        return objectMapper.readValue(json, WeatherResponse.class);
    }


    //builds the url with the information we need
    public String buildWeatherApiUrl(String url,
                                            String apiKey,
                                            String lat,
                                            String lon,
                                            String units,
                                     Instant instant) {

        if (isNullOrEmpty(url) || isNullOrEmpty(apiKey) || isNullOrEmpty(lat) || isNullOrEmpty(lon) || isNullOrEmpty(units)) {
            return null;
        }

        return UriComponentsBuilder
                .fromUriString(url)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", units)
                .queryParam("exclude", "minutely,hourly,alerts")
                .queryParam("dt", instant.getEpochSecond())
                .queryParam("appid", apiKey)
                .toUriString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public Optional<WeatherResponseDto> getWeatherFiveDays()  {
        ApiConnectionWeather apiConnection = new ApiConnectionWeather();
        StringBuilder responseContent = new StringBuilder();

        try {
            String urlString = buildWeatherApiUrl(urlForecastFiveDays, apiKey, latitude, longitude,"metric", Instant.now());
            Optional<StringBuilder> response = apiConnection.connectionApiWheather(urlString);
            if (response.isPresent()) {
                responseContent = response.get();
                return Optional.of(WeatherResponseConverter.toDto(toWeatherResponse(responseContent.toString())));
            }
        }catch(IOException io){
            io.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();


    }



}
