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
    
    The API return a JSON with the following structure:
        {
        "lat": 49.837,
        "lon": -97.1701,
        "timezone": "America/Winnipeg",
        "date": "2024-01-27",
        "minTempCelsius": -13.38,
        "minTempFahrenheit": 7.916,
        "daily": {
            "dt": 1706378400,
            "sunrise": 1706364537,
            "sunset": 1706397210,
            "moonrise": 1706404860,
            "moonset": 1706369220,
            "temp": {
                "day": -5.29,
                "min": -13.38,
                "max": -3.82,
                "night": -9.69,
                "eve": -9.81,
                "morn": -12.8
            },
            "pressure": 1024,
            "humidity": 92,
            "uvi": 1.15,
            "clouds": 32,
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
                "day": -5.29,
                "night": -13.83,
                "eve": -9.81,
                "morn": -12.8
            },
            "dew_point": -6.63,
            "wind_speed": 2.38,
            "wind_deg": 248,
            "wind_gust": 4.06
        }
    }

    The API return the following HTTP status code:
        200: OK
        404: Not Found
        500: Internal Server Error

### How to use the API
    After run your project, you can use the following URL in your browser:
         http://localhost:8080/weather/coldest-day-next-5-days
    You can use the following parameters:
        lon: longitude of the city
        lat: latitude of the city
    Example:    
        http://localhost:8080/weather/coldest-day-next-5-days?lon=-97.1701&lat=49.837
    It's optional, if no parameters are provided, the API will use the default values:
        lon: -97.1701
        lat: 49.837

### Next Steps
    Create an interface using a good UI/UX get the weather from other cities. Could be a web page or a mobile app.
    Create a cache to improve the performance of the API.
    Create a database to store the information of the cities.
