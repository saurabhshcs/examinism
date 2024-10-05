FROM eclipse-temurin:22-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# EXPOSE 6003
ENTRYPOINT ["java","-jar","/app.jar"]