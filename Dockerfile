FROM maven:3.6-openjdk-11-slim AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean install

FROM adoptopenjdk/openjdk11:alpine-jre
COPY --from=MAVEN_BUILD ./target/*.jar /trip-spring.jar
ENTRYPOINT ["java","-jar","trip-spring.jar"]
