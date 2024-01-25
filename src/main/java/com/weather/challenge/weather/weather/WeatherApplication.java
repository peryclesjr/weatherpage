package com.weather.challenge.weather.weather;

import com.weather.challenge.weather.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WeatherApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(WeatherApplication.class, args);
    }


}
