FROM maven:3.8.2-openjdk-16-slim AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN mvn clean package

COPY src ./src

RUN chmod +x mvnw

CMD ["./mvnw", "spring-boot:run"]
