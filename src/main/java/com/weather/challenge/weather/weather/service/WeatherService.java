package com.weather.challenge.weather.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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




    public Optional<WeatherResponseDto> getWeatherFiveDays()  {
        ApiConnectionWeather apiConnection = new ApiConnectionWeather();
        StringBuilder responseContent = new StringBuilder();

        try {
            String urlString = apiConnection.buildWeatherApiUrl(urlForecastFiveDays, apiKey, latitude, longitude,"metric", Instant.now());
            Optional<StringBuilder> response = apiConnection.connectionApiWheather(urlString);
            if (response.isPresent()) {
                responseContent = response.get();
                WeatherResponse weatherResponse = toWeatherResponse(responseContent.toString());
                weatherResponse.setDaily(onlyFiveDays(weatherResponse.getDaily()));
                return Optional.of(WeatherResponseConverter.toDto(weatherResponse));
            }
        }catch(IOException io){
            io.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();

    }

    private WeatherResponse toWeatherResponse(String json) throws IOException {
        return objectMapper.readValue(json, WeatherResponse.class);
    }

    //Api returns 8 days, but we only need 5, today we remove the 0 day and the last  two days after
    //it's simple to change the number of days, just change the parameters of the subList method
    private List<DailyWeather> onlyFiveDays(List<DailyWeather> dailyWeatherList) {
        if (dailyWeatherList.size() < 6) {
            return dailyWeatherList;
        }
        dailyWeatherList.sort(Comparator.comparingLong(DailyWeather::getDt));

        return dailyWeatherList.subList(1, 6);
    }
}
