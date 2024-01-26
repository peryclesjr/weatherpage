# Weather Challenge
### Description
    It's a little project to demostrate the use of the API of OpenWeatherMap, v2.5.
    this project is created using java 8, spring boot, maven.
    The project is a maven project, so you can import it in your favorite IDE.
    To run the project, you can use the command: 
        mvn spring-boot:run
    To run the test, you can use the command:
        mvn test    
### API 
    The API is a REST API, and it's a GET method, the URL is:
        http://localhost:8080/weather/coldest-day-next-5-days
    The API has two parameters: 
    The API return a JSON with the following structure:
        {
        "lat": 49.837,
        "lon": -97.1701,
        "timezone": "America/Winnipeg",
        "daily": {
            "dt": 1706378400,
            "sunrise": 1706364537,
            "sunset": 1706397210,
            "moonrise": 1706404860,
            "moonset": 1706369220,
            "temp": {
                "day": -5.77,
                "min": -13.8,
                "max": -4.3,
                "night": -9.74,
                "eve": -10.6,
                "morn": -13.01
            },
            "pressure": 1025,
            "humidity": 93,
            "uvi": 1.15,
            "clouds": 38,
            "pop": 0,
            "rain": 0,
            "weather": [
                {
                    "id": 802,
                    "main": "Clouds",
                    "description": "scattered clouds",
                    "icon": "03d"
                }
            ],
            "moon_phase": 0.56,
            "feels_like": {
                "day": -5.77,
                "night": -14.68,
                "eve": -10.6,
                "morn": -16.52
            },
            "dew_point": -6.95,
            "wind_speed": 2.59,
            "wind_deg": 174,
            "wind_gust": 4.74
        }
    }
    The API return the following HTTP status code:
        200: OK
        404: Not Found
        500: Internal Server Error

### How to use the API
    After run your project, you can use the following URL in your browser:
         http://localhost:8080/weather/coldest-day-next-5-days