# Full Modern Enterprise Stack

This is a full stack development skeleton to build modern professional enterprise applications. In this case all configurations are for develop mode (local).
**Don't use that configurations on production mode!**

## Getting Started

These instructions will get you a copy of the project (clone repository) up and running on your local machine for development and testing purposes.


**Step 1:** Install OpenJDK on your System, if not exists

**Step 2:** Start Docker Environment

Hint: Database has to be available for the next step (no 3)
```
docker compose up -d
```

**Step 3:** Install and Build Application
```
./mvnw package
```

* Installing node and npm
* Executing npm install
* Executing npm run build
* Installing all maven dependencies
* Creating two database schemas (avondock,system)
* Seeding Database
* Running Test environment
* Building jar file
* Creating docker api image

**Step 4:** Start Backend API with hot reloading

Terminal 1:
```
cd api && ./mvnw spring-boot:run
```

**Step 5:** Start Frontend

Terminal 2:
```
cd frontend && npm run serve
```

ATTENTION: hot reloading will so not work, if you want hot reloading ->

Example website: (rest of available commands you will find in the package.json)
```
cd frontend && npm run start:website
```

**Step 6 (optional):** Start Admin Server

Terminal 3:
```
cd admin ./mvnw spring-boot:start
```

## Enterprise Stack

What the hell goes on here :-)

* Auditing any changes (envers)
* Eventsourcing of your aggregates (does we need auditing anymore?) - (Axon)
* Frontend Domain Driven Boundaries (encapsulate with shell pattern) - (nx)
* JWT Token authorization, with an iam database structure
* Switching relation databases to no-sql without change any code

### Tipps and Tricks

* Don't use Mapping files for your models, first of all spring boot, will per default not read such files and it's in java not recommended, because of the jpa and their abstraction. If you want somehow else a persistence mapping file, you have to override this behaviour by your self...or you can also switch to php :-) 

## How to use

#### Frontend
* Website App: http://localhost:4200
* Admin App: http://localhost:4201
* Mobile App: http://localhost:4202

#### Backend
* API Server: http://localhost:8098
* Rest Docs: http://localhost:8098/swagger-ui/#/
* Axon Dashboard: http://localhost:8024
* Jobrunr Dashboard: http://localhost:8000/dashboard
* Admin Spring Boot Server: http://localhost:8080

## How to do's

#### Activate Cassandra

Comment out the configuration annotation of both persistence configs and uncomment
the configuration annotation of the cassandra config and restart the application

**ATTENTION:** The search criteria actually not supported by OGM, so if you switch to cassandra
you have to deactivate the SeedEventListener and you cannot use the ex. findByCarParkId in the
repository interface. The hibernate developers are currently developing hibernate 6.0, after
release, they want to continue with the OGM

## Deployment

Add additional notes about how to deploy this on a production system.

## Resources

Add links to external resources for this project, such as CI server, bug tracker, etc.

## Thanks to

* **Spring Boot Framework** ...completely unreachable
* **Axon Framework** ...nice work, the best cqrs framework and interpretation of CQRS analogous to M.Fowler
* **Angular** ...SOLID, you will make enterprise, you don't have a alternative
* **Nrwl** ...thumb up
* **Hibernate** ...no words, awesome...with the ogm this orm play in another level
* **Java Community** ... all the JSRs, to create a programming language, that's for business development unreachable
* **All other** ...they have published tutorials like (Baeldung) and given the right answers on Stackoverflow

...Thank you very much!
