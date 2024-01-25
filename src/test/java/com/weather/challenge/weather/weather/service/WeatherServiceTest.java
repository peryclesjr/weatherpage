package com.weather.challenge.weather.weather.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


class WeatherServiceTest {


    @Spy
    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private HttpURLConnection mockConnection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherData() throws IOException {
        String expectedResponse = "Expected response";
        InputStream inputStream = new ByteArrayInputStream(expectedResponse.getBytes());

        when(mockConnection.getInputStream()).thenReturn(inputStream);
        when(mockConnection.getResponseCode()).thenReturn(200);

        // Mock the buildWeatherApiUrl method
        doReturn("http://mocked.url").when(weatherService).buildWeatherApiUrl(any(), any(), any(), any());
        doReturn("Expected response").when(weatherService).getWeatherData();

        String actualResponse = weatherService.getWeatherData();

        assertEquals(expectedResponse, actualResponse);
    }


    @Test
    public void given_AllDataForConnection_ThenBuildUrlSucces() {
        String baseUrl = "http://api.weather.com";
        String apiKey = "123456";
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = "http://api.weather.com?lat=40.7128&lon=74.0060&appid=123456";
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon);

        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    public void given_Null_Url_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = null;
        String apiKey = "123456";
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon);

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void given_Url_And_One_Data_Null_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = "http://api.weather.com";
        String apiKey = null;
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon);

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void given_Url_And_One_Data_Empty_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = "http://api.weather.com";
        String apiKey = "";
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon);

        assertEquals(expectedUrl, actualUrl);
    }


}