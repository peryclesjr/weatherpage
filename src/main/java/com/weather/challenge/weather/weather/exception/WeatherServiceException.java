package com.weather.challenge.weather.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(String message) {
        super(message);
    }
    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
