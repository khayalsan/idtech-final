FROM eclipse-temurin:21-jdk as build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew :ms:order:bootJar --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/ms/order/build/libs/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]