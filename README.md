# Spring Boot Weather API

Small Spring Boot project built to learn the Spring framework

------------------------------------------------------------------------

## Endpoints

### GET /weather/current?city=CityName

Returns current weather for a given city.

``` http
GET http://localhost:8080/weather/current?city=Bucharest
```

------------------------------------------------------------------------

### GET /weather/favorites/current

Returns current weather for all cities stored in `data/favorites.json`.

``` http
GET http://localhost:8080/weather/favorites/current
```

------------------------------------------------------------------------

### POST /favorites

Adds a city to favorites.

``` http
POST http://localhost:8080/favorites
Content-Type: application/json
```

Request body:

``` json
{
  "city": "Bucharest"
}
```

------------------------------------------------------------------------

## Favorites Storage

Favorites are stored locally in:

``` text
data/favorites.json
```

Example:

``` json
{
  "1": "Bucharest",
  "2": "London"
}
```

------------------------------------------------------------------------

## Run

From project root:

``` bash
mvn spring-boot:run
```

App runs on:

``` text
http://localhost:8080
```
