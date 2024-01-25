package com.weather.challenge.weather.weather.utils;

import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RequiredArgsConstructor
public class ApiConnectionWeather {
    public Optional<StringBuilder> connectionApiWheather(String urlString) {
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

            return Optional.of(responseContent);

        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();

        }
        return Optional.empty();
    }
}
