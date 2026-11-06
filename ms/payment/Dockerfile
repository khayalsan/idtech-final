FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew :ms:payment:bootJar --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/ms/payment/build/libs/*.jar app.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "app.jar"]