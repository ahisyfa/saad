FROM openjdk:8-jdk-alpine

COPY . .

RUN ./mvnw clean package

# java -jar /usr/local/runme/app.jar
ENTRYPOINT ["java", "-Xmx510m", "-jar", "target/saad-0.0.1-SNAPSHOT.jar"]

EXPOSE 10000