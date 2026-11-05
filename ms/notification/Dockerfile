FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew :ms:notification:bootJar --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/ms/notification/build/libs/*.jar app.jar

EXPOSE 8003

ENTRYPOINT ["java", "-jar", "app.jar"]