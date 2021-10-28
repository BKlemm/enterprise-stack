## Backend API

#### Activate Profiles (for Axon Instances)


Uncomment the @Profile("command") annotation in the Aggregate

```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=command
```

Uncomment the @Profile("query") annotation in the Projection class

```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=query
```

Uncomment the @Profile("rest") annotation in both gateways

```
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=rest
```

This will start 3 own instances the command way on port 8099 the query context on port 8097
and the rest endpoints has to keep the port 8098, for frontend communications

On localhost:8024/#overview you will set now this:

![Axon with 3 profiles](docs/axon.png)

#### Testing manual your Restendpoints

Open http://localhost:8098/swagger-ui/#/car-park-query-endpoint/listCarParksUsingGET

![Swagger Rest UI Testing](docs/swagger.png)
