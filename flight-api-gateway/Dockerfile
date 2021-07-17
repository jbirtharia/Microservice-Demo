FROM openjdk:11-jre-slim-buster
ADD target/flight-api-gateway-0.0.1-SNAPSHOT.jar flight-api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /flight-api-gateway-0.0.1-SNAPSHOT.jar"]