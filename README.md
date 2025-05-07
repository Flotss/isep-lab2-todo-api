# Todo API

A Spring Boot-based REST API for managing Todo items, implementing a hexagonal architecture pattern.

## Features

- Create and list Todo items
- Configurable storage backends (In-Memory or CSV file)
- Input validation
- RESTful API endpoints
- Hexagonal architecture implementation

## Business Rules

A Todo item must follow these rules:
- Name is required and must be shorter than 64 characters
- Name must be unique (no duplicate names allowed)
- Due date is optional and must be a valid date when provided
- If a due date is provided, it must be a future date

## API Endpoints

### Create a Todo
```
POST /todos
```
Request body:
```json
{
    "name": "Todo name",
    "date": "2024-03-20"  // Optional, must be a future date if provided
}
```
Responses:
- 201: Todo created successfully
- 400: Business rules not respected

### List All Todos
```
GET /todos
```
Response:
- 200: Returns list of all todos

## Architecture

The project follows a hexagonal architecture (also known as ports and adapters) with three main layers:

### Presentation Layer
- Contains the REST API endpoints (`TodoController`)
- Handles HTTP requests and responses
- Validates input data

### Application Layer
- Contains business logic (`TodoManager`)
- Defines the repository interface (`ITodoRepository`)
- Implements business rules

### Persistence Layer
Two implementations available:
1. In-Memory Repository (`TodoInMemoryRepository`)
   - Stores todos in memory
   - Data is lost when application restarts
2. CSV Files Repository (`TodoCsvFilesRepository`)
   - Persists todos in a CSV file
   - Data persists between application restarts

## Configuration

The repository type can be configured in `application.properties`:
```properties
repository.type=INMEMORY  # or CSV
```

## Technical Stack

- Java 21
- Spring Boot 3.4.5
- Gradle 8.13
- Lombok for reducing boilerplate code
- Spring Validation for input validation

## Getting Started

1. Clone the repository
2. Configure the repository type in `application.properties`
3. Run the application:
```bash
./gradlew bootRun
```

## Project Structure

```
src/main/java/com/isep/lab2todoapi/
├── application/
│   ├── ITodoRepository.java
│   ├── RepositoryType.java
│   └── TodoManager.java
├── config/
│   └── RepositoryConfig.java
├── persistence/
│   ├── csvfiles/
│   │   └── TodoCsvFilesRepository.java
│   └── inmemory/
│       └── TodoInMemoryRepository.java
├── presentation/
│   └── TodoController.java
├── GlobalExceptionHandler.java
├── IsepLab2TodoApiApplication.java
└── Todo.java
```

## Testing

Run the tests using:
```bash
./gradlew test
```

## Error Handling

The application includes a global exception handler that provides detailed error messages for:
- Validation failures (400 Bad Request)
  - Field validation errors (e.g., required fields, future date validation)
  - Each field error includes the field name and error message
- Illegal argument exceptions (400 Bad Request)
  - Business rule violations
  - Invalid input data

Example error response for validation:
```json
{
    "date": "La date doit être dans le futur",
    "name": "Le nom est requis"
}
```

## License

This project is part of the ISEP Software Engineering course.