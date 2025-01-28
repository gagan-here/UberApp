# UberApp - Spring Boot Project

## Overview
This is a Spring Boot application for an Uber-like service, built using Java 17 and Spring Boot 3.4.0. The project implements various features including user authentication, geospatial operations, email services, and RESTful APIs.

## Technology Stack

### Core Dependencies
- **Spring Boot 3.4.0** - The main framework providing core functionality
- **Java 17** - The required JDK version for this project

### Database & ORM
- **Spring Data JPA** - For database operations and ORM functionality
- **PostgreSQL** - Main database system
- **Hibernate Spatial** - For handling geospatial data operations

### Security
- **Spring Security** - Handles authentication and authorization
- **JWT (JSON Web Tokens)** - For secure token-based authentication
    - jjwt-api
    - jjwt-impl
    - jjwt-jackson

### API Documentation & Development Tools
- **SpringDoc OpenAPI UI** - API documentation using OpenAPI 3.0
- **Spring Boot Actuator** - For monitoring and managing application
- **Lombok** - Reduces boilerplate code
- **ModelMapper** - For object mapping between DTOs and entities

### Testing
- **Spring Boot Test** - For unit and integration testing
- **Spring Security Test** - Security-specific testing utilities
- **Testcontainers** - For integration testing with containerized dependencies
    - PostgreSQL container support
    - JUnit Jupiter integration

### Development Tools
- **Spotless** - Code formatting tool with Google Java Format
- **Spring Boot DevTools** - Development-time tools and features

### Additional Features
- **Spring Boot Mail Starter** - For email functionality
- **Spring WebFlux** - Reactive web support
- **Spring Boot Starter Web** - For building web applications

## Setup and Installation

1. Ensure you have Java 17 installed
2. Install PostgreSQL database
3. Clone the repository
4. Update application.properties with your database configuration
5. Run `mvn clean install` to build the project

## Database Configuration

Configure your PostgreSQL database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Running the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, you can access the API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI specification: `http://localhost:8080/v3/api-docs`

## Development

### Code Formatting
The project uses Spotless with Google Java Format. To format your code:

```bash
mvn spotless:apply
```

### Testing
To run tests:

```bash
mvn test
```

## Monitoring

Spring Boot Actuator endpoints are available at `/actuator/*` for monitoring application health and metrics.

## Security

The application uses JWT-based authentication. Make sure to configure your security settings in the security configuration class.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request