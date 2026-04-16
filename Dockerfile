FROM amazoncorretto:21-al2023-jdk
COPY target/staminaBackend-0.0.1-SNAPSHOT.jar /api-v1.jar
ENTRYPOINT ["java", "-jar", "/api-v1.jar"]