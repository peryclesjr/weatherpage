package com.weather.challenge.weather.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.challenge.weather.weather.exception.WeatherServiceException;
import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.details.DailyWeather;
import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.utils.ApiConnectionWeather;
import com.weather.challenge.weather.weather.utils.WeatherResponseConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
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




    public Optional<WeatherResponseDto> getWeatherFiveDays(String lat, String lon)  {
        ApiConnectionWeather apiConnection = new ApiConnectionWeather();
        String responseContent;

        try {
            String urlString = apiConnection.buildWeatherApiUrl(urlForecastFiveDays, apiKey, lat, lon, Instant.now());
            Optional<String> response = apiConnection.connectionApiWheather(urlString);
            if (response.isPresent()) {
                responseContent = response.get();
                WeatherResponse weatherResponse = toWeatherResponse(responseContent.toString());
                weatherResponse.setDaily(onlyFiveDays(weatherResponse.getDaily()));
                return Optional.of(WeatherResponseConverter.toDto(weatherResponse));
            }
        }catch(IOException io){
            throw new WeatherServiceException("Failed to get weather data", io);
        }catch(Exception e){
            throw new WeatherServiceException("Unexpected error occurred", e);
        }
        return Optional.empty();

    }

    private WeatherResponse toWeatherResponse(String json) throws IOException {
        return objectMapper.readValue(json, WeatherResponse.class);
    }

    //Api returns 8 days, but we only need 5, we remove the 0 day(today) and the last two days from the list
    private List<DailyWeather> onlyFiveDays(List<DailyWeather> dailyWeatherList) {
        if (dailyWeatherList.size() < 6) {
            return dailyWeatherList;
        }
        dailyWeatherList.sort(Comparator.comparingLong(DailyWeather::getDt));

        return dailyWeatherList.subList(1, 6);
    }
}
