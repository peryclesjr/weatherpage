package com.weather.challenge.weather.weather.service;

import com.weather.challenge.weather.weather.model.dto.WeatherResponseDto;
import com.weather.challenge.weather.weather.model.details.CurrentWeather;
import com.weather.challenge.weather.weather.utils.ApiConnectionWeather;
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
import java.time.Instant;
import java.util.Optional;

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



    @Mock
    private ApiConnectionWeather apiConnection;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testForecastTiveDays() throws IOException {
        WeatherResponseDto expected = new WeatherResponseDto();

        Instant instant = Instant.now();
        CurrentWeather current = new CurrentWeather();
        current.setDt(instant.getEpochSecond());


        expected.setLat(40.7128);
        expected.setLon(74.0060);
        expected.setTimezone("America/New_York");




        InputStream inputStream = new ByteArrayInputStream(expected.toString().getBytes());

        when(mockConnection.getInputStream()).thenReturn(inputStream);
        when(mockConnection.getResponseCode()).thenReturn(200);

        // Mock the buildWeatherApiUrl method
        doReturn("http://mocked.url").when(weatherService).buildWeatherApiUrl(any(), any(), any(), any(), any(), any());
        WeatherResponseDto actualResponse = new WeatherResponseDto();
        CurrentWeather currentResponse = new CurrentWeather();
        currentResponse.setDt(instant.getEpochSecond());
        actualResponse.setLat(40.7128);
        actualResponse.setLon(74.0060);
        actualResponse.setTimezone("America/New_York");





        doReturn(Optional.of(actualResponse)).when(weatherService).getWeatherFiveDays();

        Optional<WeatherResponseDto> weatherResponse = weatherService.getWeatherFiveDays();

        assertEquals(expected, weatherResponse.get());
    }


    @Test
    public void given_AllDataForConnection_ThenBuildUrlSucces() {
        String baseUrl = "http://api.weather.com";
        String apiKey = "123456";
        String lat = "40.7128";
        String lon = "74.0060";

        Instant instant = Instant.now();

        String expectedUrl = "http://api.weather.com?lat=40.7128&lon=74.0060&units=metric&exclude=minutely,hourly,alerts&dt="+instant.getEpochSecond()+"&appid=123456";
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon, "metric", instant);

        assertTrue(expectedUrl.equalsIgnoreCase(actualUrl));
    }
    @Test
    public void given_Null_Url_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = null;
        String apiKey = "123456";
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon, "metric", Instant.now());

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void given_Url_And_One_Data_Null_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = "http://api.weather.com";
        String apiKey = null;
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon, "metric", Instant.now());

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void given_Url_And_One_Data_Empty_ForConnection_ThenBuildUrlFailure() {
        String baseUrl = "http://api.weather.com";
        String apiKey = "";
        String lat = "40.7128";
        String lon = "74.0060";

        String expectedUrl = null;
        String actualUrl = weatherService.buildWeatherApiUrl(baseUrl, apiKey, lat, lon, "metric", Instant.now());

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testConnectionApiWeather() throws Exception {
        String testResponse = "Test response";
        InputStream testInput = new ByteArrayInputStream(testResponse.getBytes());

        when(mockConnection.getInputStream()).thenReturn(testInput);

//        Optional<StringBuilder> result = apiConnection.connectionApiWheather(mockUrl.toString());

//        assertEquals(testResponse, result.get().toString());
    }


}