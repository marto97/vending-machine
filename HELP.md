# Getting Started

This is a simple vending machine implemented in Java with Spring.

## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [PostgreSQL 16](https://postgresapp.com/downloads.html)


## How to Build and Run

1. Clone the repository.
2. Navigate to the project directory.
3. There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.martin.vending.VendingApplication` class from your IDE.

### Build

```bash
./mvnw clean install
```

### Run

Navigate to the project `target` directory.

```bash
java -jar vending-0.0.1-SNAPSHOT.jar
```

### Add Product 
```
POST http://localhost:8080/api/v1/vending
Content-Type: application/json

{
"name": "Cola",
"type": "snacks",
"location": "A7",
"cost": 1.50
}
```

### Update Product by ID
```
PUT http://localhost:8080/api/v1/vending/4?name=ColaLight&type=drinks&location=A8&cost=1.60
Content-Type: application/json
```

### Remove Product by ID
```
DELETE http://localhost:8080/api/v1/vending/1
```
### Purchase Product by Location
```
POST http://localhost:8080/purchaseProduct
Content-Type: application/json

{
  "insertedCoins": [
    {
      "value": 0.50,
      "weight": 5.0,
      "diameter": 22.5
    },
    {
      "value": 1.00,
      "weight":7.0,
      "diameter": 24.5
    }
  ],
  "productLocation": "A1"
}
```
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

