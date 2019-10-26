FROM openjdk:8-jdk-alpine as build-stage
ADD . /Snorlax
WORKDIR /Snorlax
RUN chmod +x ./gradlew && ./gradlew shadowJar

FROM openjdk:8-jdk-alpine as exec-stage
COPY --from=build-stage /Snorlax/build/libs/snorlax-0.0.1-all.jar .

ENTRYPOINT ["java", "-jar", "snorlax-0.0.1-all.jar"]