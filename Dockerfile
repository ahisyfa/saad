FROM openjdk:8-jdk-alpine

COPY . .

RUN ./mvnw clean package

# java -jar /usr/local/runme/app.jar
ENTRYPOINT ["java", "-Xmx510m", "-Xss2k","-jar", "target/saad-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080