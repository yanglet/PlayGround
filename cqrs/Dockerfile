FROM amazoncorretto:17.0.2
WORKDIR /cqrs
COPY ./build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]