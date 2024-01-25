package com.weather.challenge.weather.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.challenge.weather.weather.model.WeatherResponse;
import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.utils.WeatherResponseConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;


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


    public WeatherResponseDto getWeatherData() {
        StringBuilder responseContent = new StringBuilder();

        //fill our Url with the information we need
        String urlString = buildWeatherApiUrl(urlBase, apiKey, latitude, longitude, "metric");
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


            return WeatherResponseConverter.toDto(toWeatherResponse(responseContent.toString()));

        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();

        }
        return null;
    }


    //converts the json to a WeatherResponse object
    public WeatherResponse toWeatherResponse(String json) throws IOException {
        return objectMapper.readValue(json, WeatherResponse.class);
    }


    //builds the url with the information we need
    public String buildWeatherApiUrl(String url,
                                            String apiKey,
                                            String lat,
                                            String lon,
                                            String units) {

        if (isNullOrEmpty(url) || isNullOrEmpty(apiKey) || isNullOrEmpty(lat) || isNullOrEmpty(lon) || isNullOrEmpty(units)) {
            return null;
        }

        return UriComponentsBuilder
                .fromUriString(url)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("units", units)
                .queryParam("exclude", "minutely,hourly,alerts")
                .queryParam("dt", Instant.now().getEpochSecond())
                .queryParam("appid", apiKey)
                .toUriString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public WeatherResponseDto getWeatherFiveDays() {
        StringBuilder responseContent = new StringBuilder();

        //fill our Url with the information we need
        String urlString = buildWeatherApiUrl(urlForecastFiveDays, apiKey, latitude, longitude, "metric");
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

            return WeatherResponseConverter.toDto(toWeatherResponse(responseContent.toString()));

        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();

        }
        return null;
    }

}
