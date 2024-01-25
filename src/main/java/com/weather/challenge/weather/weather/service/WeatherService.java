package com.weather.challenge.weather.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.challenge.weather.weather.model.WeatherResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


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

    @Value("${weather.url}")
    private String urlBase;



    public String getWeatherData() {
        StringBuilder responseContent = new StringBuilder();

        //fill our Url with the information we need
        String urlString = buildWeatherApiUrl(urlBase, apiKey, latitude, longitude);
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


            System.out.println("Response code: " + responseContent.toString());
            return toWeatherResponse(responseContent.toString()).toString();

        }catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        finally {
            connection.disconnect();
        }


    }


    //converts the json to a WeatherResponse object
    public WeatherResponse toWeatherResponse(String json) throws IOException {
        return objectMapper.readValue(json, WeatherResponse.class);
    }


    //builds the url with the information we need
    public String buildWeatherApiUrl(String baseUrl,
                                            String apiKey,
                                            String lat,
                                            String lon) {

        if (isNullOrEmpty(baseUrl) || isNullOrEmpty(apiKey) || isNullOrEmpty(lat) || isNullOrEmpty(lon)) {
            return null;
        }

        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .toUriString();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
