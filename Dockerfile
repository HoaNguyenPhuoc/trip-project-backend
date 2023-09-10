FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY ${JAR_FILE} trip-spring.jar
ENTRYPOINT ["java","-jar","trip-spring.jar"]