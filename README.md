# SprintMind Hackathon
# Spring Boot + Angular Template

Monorepo template with:
- Spring Boot backend
- Angular frontend
- Example REST controller
- Angular service calling the backend
- Proxy config for local development

## Requirements

- Java 17+
- Maven 3.9+ or Maven Wrapper
- Node.js 18+ or 20+
- Angular CLI installed globally, or use `npx ng`

## Run backend

```bash
cd backend
./mvnw spring-boot:run
```

If the wrapper files are not present, use:

```bash
mvn spring-boot:run
```

Backend runs at:

- http://localhost:8080/api/hello

## Run frontend

```bash
cd frontend
npm install
npm start
```

Frontend runs at:

- http://localhost:4200

## How it works

- Angular calls `/api/hello`.
- `proxy.conf.json` forwards that request to `http://localhost:8080`.
- The Spring controller returns a JSON response like:

```json
{ "message": "Hello from Spring Boot" }
```

## Create the repo

```bash
mkdir git-springboot-angular-template
cd git-springboot-angular-template
git init
```