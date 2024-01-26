package com.weather.challenge.weather.weather.utils;

import com.weather.challenge.weather.weather.exception.WeatherServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
public class ApiConnectionWeather {
    public Optional<String> connectionApiWheather(String urlString) {
        StringBuilder responseContent = new StringBuilder();
        String line;

        HttpURLConnection connection = null;

        try  {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(),
                            StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }

            return Optional.of(responseContent.toString());

        }catch (IOException io) {
            throw new WeatherServiceException("Failed to get weather data", io);
        }
        catch (Exception e) {
            throw new WeatherServiceException("Unexpected error occurred", e);
        }
        finally {
            connection.disconnect();

        }
    }

    public String buildWeatherApiUrl(String url,
                                     String apiKey,
                                     String lat,
                                     String lon,
                                     Instant instant) {

        if (isNullOrEmpty(url) || isNullOrEmpty(apiKey) || isNullOrEmpty(lat) || isNullOrEmpty(lon) ) {
            return null;
        }

        return UriComponentsBuilder
                .fromUriString(url)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", "metric")
                .queryParam("exclude", "minutely,hourly,alerts")
                .queryParam("dt", instant.getEpochSecond())
                .queryParam("appid", apiKey)
                .toUriString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
