FROM openjdk:8-jdk-alpine

COPY . .

RUN ./mvnw clean package

EXPOSE 8080

# java -jar /usr/local/runme/app.jar
ENTRYPOINT ["java", "-jar", "target/saad-0.0.1-SNAPSHOT.jar"]