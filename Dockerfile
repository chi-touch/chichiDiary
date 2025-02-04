FROM maven:3.8.7 as build
COPY . .
RUN mvn -B clean package -DskipTests
FROM openjdk:17
COPY --from=build target/*.jar diarywork.jar
# ENV SPRING_PROFILES_ACTIVE=$(PROFILE)
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "diarywork.jar"]
