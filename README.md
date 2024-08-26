# SWAPI Integration Service
## Overview
The SWAPI Integration Service is a Java-based application designed to integrate with the Star Wars API (SWAPI). This service allows you to retrieve detailed information about Star Wars characters, vehicles, starships, planets, and films. The application is built using Spring Boot and follows a clean architecture design pattern, separating concerns between application logic, domain models, and infrastructure services.

## Features
Character Information Retrieval: Fetch detailed information about a character, including their home planet, films...
Fastest Vehicle/Starship Calculation: Determine the fastest vehicle or starship driven by a character.
SWAPI Client Integration: Integration with the SWAPI to fetch real-time dataroject Structure

## Project structure 
The project structure is following a clean architecture pattern:
- Application Layer: Contains the application services and mappers responsible for orchestrating operations and transforming domain models to DTOs and responses.
- Domain Layer: Contains the core domain models and business logic.
- Infrastructure Layer: Contains the SWAPI client, responsible for interacting with the external Star Wars API.
- Test Layer: Contains unit and integration tests, including data factories for creating mock data.

## Prerequisites
- Java 17 or higher
- Maven 3.8.0 or higher
- Docker (if using the Dockerfile)

# Getting Started

## Clone the Repository
```
git clone https://github.com/JavierGarciaGarzon/SWapiProyect
```
## Build the Project
To build the project, use the following Maven command:
```
mvn clean install
```
## Run the Application
You can run the application using Maven:
```
mvn spring-boot:run
```
## Running Tests
The project includes unit tests to ensure the functionality of the application. You can run the tests using:
```
mvn test
```
## Using Docker
### Build the Docker Image
To build the Docker image for the application, use the following command:
```
docker build -t swapi-integration-service .
```
### Run the Docker Container
After building the Docker image, you can run the application inside a container:
```
docker run -p 8080:8080 swapi-integration-service
```
This will start the application, and it will be accessible at http://localhost:8080.

## Usage
After running the application, you can access the API endpoints to fetch character information. For example, to fetch information about "Luke Skywalker", you would use:
```
GET /api/people?name=Luke Skywalker
```

