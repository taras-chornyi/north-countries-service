## Northern Countries Service

This service takes IP addresses, check which countries they are from and if there are any returns list of countries from the northern hemisphere.

In order to get information about IP address this service uses IP Vigilante API: [**IP Vigilante API Developer Docs**](https://www.ipvigilante.com/api-developer-docs/).

Latitude value uses for decide if IP address comes from a country from the northern hemisphere.

### Testing and running project

For running integration tests just perform following command:
 
    ./gradlew clean build
    
This service has ability to be started with a single Gradle command:

    ./gradlew bootRun
    
### Example use case

Request:

    curl "http://localhost:8888/northcountries?ip=8.8.8.8&ip=8.8.0.0&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"
    
Response: 

    {  
       "northcountries":[  
          "Colombia",
          "Japan",
          "United States"
       ]
    }

### Swagger UI

This service also has ability to get access for service from the Swagger UI console:

[**http://localhost:8888/swagger-ui.html**](http://localhost:8888/swagger-ui.html)

                    
