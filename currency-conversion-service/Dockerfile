FROM openjdk:11-jre-slim-buster
ADD target/currency-conversion-service-0.0.1-SNAPSHOT.jar currency-conversion-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /currency-conversion-service-0.0.1-SNAPSHOT.jar"]