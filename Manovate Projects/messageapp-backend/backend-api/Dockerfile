# -------- STAGE 1 : Build --------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN ./mvnw -B dependency:resolve dependency:resolve-plugins

COPY src src

RUN ./mvnw clean package -DskipTests

# -------- STAGE 2 : Run --------
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY --from=build /app/target/backend-api-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
