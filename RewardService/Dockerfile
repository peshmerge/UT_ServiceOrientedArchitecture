#### Stage 1: Build the application
FROM amazoncorretto:11 as build

# Set the current working directory inside the image
WORKDIR /app

# Copy Gradle executables to the container
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN ./gradlew dependencies

# Copy the project source
COPY src src

# Package the application
# RUN ./gradlew build unpack -x test --old one
RUN ./gradlew build

RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

#### Stage 2: A minimal docker image with command to run the app
FROM adoptopenjdk:11-jre-hotspot
#FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENV PORT 9093
EXPOSE 9093
ENTRYPOINT ["java","-cp","app:app/lib/*","com.utwente.ratefy.RewardService.RewardServiceApplication"]