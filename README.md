# Inditex Challenge

### Exercise statement
```
Aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
```

## Technologies 
- [Java 11](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8.* +](https://maven.apache.org/download.cgi)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/documentation.html)
- [Data Base in Memory: H2](https://www.h2database.com/html/main.html)
- [JUnit](https://junit.org/junit5/docs/current/user-guide/)
- [Lombok Plugin](https://projectlombok.org/) 

## Running app locally
```bash
mvn clean compile package spring-boot:run
```

## Running tests locally
```bash
mvn clean test
```

## Verifying Healthy and Information endpoints
```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
```

## For HTTP API documentation see
```
http://localhost:8080/swagger-ui.html
```

### Data loaded in H2 when starting app
```
BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE              CURR
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR
```

## Testing API with Use Case example

- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
```
curl -X GET "http://localhost:8080/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-14T10%3A00%3A00" -H "accept: */*"
```
Response 
```json
{
  "brandId": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priceList": 1,
  "productId": 35455,
  "priority": 0,
  "price": 35.5,
  "currency": "EUR"
}
```

- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
```
curl -X GET "http://localhost:8080/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-14T16%3A00%3A00" -H "accept: */*"
```
Response
```json
{
  "brandId": 1,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "priceList": 2,
  "productId": 35455,
  "priority": 1,
  "price": 25.45,
  "currency": "EUR"
}
```

- Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
```
curl -X GET "http://localhost:8080/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-14T21%3A00%3A00" -H "accept: */*"
```
Response
```json
{
  "brandId": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priceList": 1,
  "productId": 35455,
  "priority": 0,
  "price": 35.5,
  "currency": "EUR"
}
```

- Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
```
curl -X GET "http://localhost:8080/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-14T21%3A00%3A00" -H "accept: */*"
```
Response
```json
{
  "brandId": 1,
  "startDate": "2020-06-15T00:00:00",
  "endDate": "2020-06-15T11:00:00",
  "priceList": 3,
  "productId": 35455,
  "priority": 1,
  "price": 30.5,
  "currency": "EUR"
}
```

- Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
```
curl -X GET "http://localhost:8080/api/v1/prices/brands/1/products/35455?applicationDate=2020-06-16T21%3A00%3A00" -H "accept: */*"
```
Response
```json
{
  "brandId": 1,
  "startDate": "2020-06-15T16:00:00",
  "endDate": "2020-12-31T23:59:59",
  "priceList": 4,
  "productId": 35455,
  "priority": 1,
  "price": 38.95,
  "currency": "EUR"
}
```

