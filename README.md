# Java Spring Boot Template with MongoDB Atlas Search

This project is a template for a java Spring Boot application querying FHIR resources in MongoDB. 


# Prerequisites

- Java 17.
- [MongoDB Cluster](https://www.mongodb.com/atlas/database) v6.0.12 or higher on MongoDB Atlas (works with an M0 free
  cluster).
- Load the [sample datasets](https://www.mongodb.com/docs/atlas/sample-data/#available-sample-datasets) as we are
  working with the `sample_mflix.movies` collection.


# Getting Started

Update the `application.properties` with your values.

```properties
spring.data.mongodb.uri=${MONGODB_URI:mongodb://localhost}
spring.data.mongodb.database=sample_mflix
spring.data.mongodb.atlas.search.index=dynamic
```

For Linux and macOS.

```bash
export MONGODB_URI="mongodb+srv://<USER>:<PWD>@free.abcde.mongodb.net/?retryWrites=true&w=majority"
./mvnw clean spring-boot:run
```

For PowerShell.

```bash
$env:MONGODB_URI="mongodb+srv://<USER>:<PWD>@free.abcde.mongodb.net/?retryWrites=true&w=majority"
mvnw.cmd clean spring-boot:run
```

# Jar File

```bash
./mvnw clean package
java -jar target/java-spring-boot-mongodb-atlas-search-1.0.0.jar
```

# Test the REST API

## cURL

```bash
curl -vs "http://localhost:8080/movies/with/indiana%20jones?limit=2" | python3 -m json.tool
```

```json

```

## Swagger 3

- Swagger 3 is already configured in this project.
- The Swagger UI can be seen
  at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).
- The JSON Open API documentation 3.0.1 is at [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs).
- The YAML Open API documentation 3.0.1 is
  at [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml).
- You can also try the entire REST API directly from the Swagger interface!

# Author

Maxime Beugnet

- maxime@mongodb.com
- MaBeuLux88 on [GitHub](https://github.com/mabeulux88)
- MaBeuLux88 in the [MongoDB Developer Community forum](https://www.mongodb.com/community/forums/u/MaBeuLux88/summary).
