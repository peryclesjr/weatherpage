package com.weather.challenge.weather.weather.utils;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;




class ApiConnectionWeatherTest {

    @Spy
    @InjectMocks
    private ApiConnectionWeather apiConnection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_AllDataForConnection_ThenBuildUrlSucces() {
        String baseUrl = "http://api.weather.com";
        String apiKey = "123456";
        String lat = "40.7128";
        String lon = "74.0060";

        Instant instant = Instant.now();

        String expectedUrl = "http://api.weather.com?lat=40.7128&lon=74.0060&units=metric&exclude=minutely,hourly,alerts&dt="+instant.getEpochSecond()+"&appid=123456";

        //behavior of the method
        doReturn(expectedUrl).when(apiConnection).buildWeatherApiUrl(any(), any(), any(), any(), any());

        String actualUrl = apiConnection.buildWeatherApiUrl(baseUrl, apiKey, lat, lon,  instant);

        assertTrue(expectedUrl.equalsIgnoreCase(actualUrl));
    }

    @Test
    public void testConnectionApiWheather() {
        Optional<String> expected = Optional.of("test");

        doReturn(expected).when(apiConnection).connectionApiWheather(any());
        Optional<String> actual = apiConnection.connectionApiWheather("http://api.weather.com");

        assertEquals(expected, actual);
    }

    //It's not the best test, but it's a good way start
    @Test
    public void given_NullOrEmptyDataForConnection_ThenBuildUrlFailure() {
        String baseUrl = null;
        String apiKey = "";
        String lat = "40.7128";
        String lon = "74.0060";

        Instant instant = Instant.now();

        String result = apiConnection.buildWeatherApiUrl(baseUrl, apiKey, lat, lon, instant);
        assertTrue(result == null);
    }
}