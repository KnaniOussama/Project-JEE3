# Project-JEE3

This project is a full-stack application with a microservices backend built with Spring Boot and a frontend built with Angular.

## Prerequisites

- Java 17
- Maven
- Node.js and Angular CLI
- Docker (for MySQL and Ollama)

## How to Build

### Backend

To build the backend, run the following command from the root of the project:

```bash
mvn clean install
```

### Frontend

To build the frontend, run the following commands from the `frontend` directory:

```bash
npm install
npm build
```

## How to Run

### 1. Start Dependencies

You can use Docker Compose to start the dependencies (MySQL and Ollama):

```bash
docker-compose up -d
```

### 2. Start Backend Services

Start the services in the following order:

1.  **discovery-service**: `java -jar discovery-service/target/discovery-service-0.0.1-SNAPSHOT.jar`
2.  **gateway-service**: `java -jar gateway-service/target/gateway-service-0.0.1-SNAPSHOT.jar`
3.  **auth-service**: `java -jar auth-service/target/auth-service-0.0.1-SNAPSHOT.jar`
4.  **product-service**: `java -jar product-service/target/product-service-0.0.1-SNAPSHOT.jar`
5.  **stock-service**: `java -jar stock-service/target/stock-service-0.0.1-SNAPSHOT.jar`
6.  **agent-ia-service**: `java -jar agent-ia-service/target/agent-ia-service-0.0.1-SNAPSHOT.jar`

### 3. Start Frontend

Start the frontend by running the following command from the `frontend` directory:

```bash
ng serve
```

The application will be available at `http://localhost:4200`.

## Testing

1.  Open your browser and navigate to `http://localhost:4200`.
2.  You will be redirected to the login page.
3.  Enter `user` for the username and `password` for the password.
4.  You will be redirected to the chat page.
5.  Enter a message and click "Send". You should see a response from the AI assistant.
