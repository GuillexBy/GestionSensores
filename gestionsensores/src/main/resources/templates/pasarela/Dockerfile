FROM maven:3.8.5-jdk-11 AS builder

WORKDIR /app-base/
COPY usuarios-pasarela/pom.xml .
RUN mvn -e -B dependency:resolve
COPY usuarios-pasarela/src ./src
RUN mvn install

WORKDIR /app/
COPY pasarela/pom.xml .
RUN mvn -e -B dependency:resolve
COPY pasarela/src ./src
RUN mvn package

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/pasarela-zuul-0.0.1-SNAPSHOT.jar /app/


CMD ["java", "-jar", "pasarela-zuul-0.0.1-SNAPSHOT.jar"]
EXPOSE 8090