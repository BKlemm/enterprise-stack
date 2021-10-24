# Avonsoft

This is a full stack development skeleton.

## Getting Started

Step 0: Clone Repository

Step 1: Install OpenJDK on your System, if not exists

Step 2: Start Docker Environment
```
docker compose up -d
```

Step 3: Install and Build Application
```
./mvnw package
```

* Installing npm
* Executing npm install
* Executing npm run build
* Installing all maven dependencies
* Creating two database schemas (avondock,system)
* Seeding Database
* Running Test environment
* Building jar file
* Creating docker api image

Step 4: Start Backend API with hot reloading
```
cd api && ./mvnw spring-boot:run
```

Step 5: Start Frontend in dev mode
```
cd frontend && npm run serve
```

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

What things you need to install the software and how to install them.

```
Examples
```

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

## Deployment

Add additional notes about how to deploy this on a production system.

## Resources

Add links to external resources for this project, such as CI server, bug tracker, etc.
