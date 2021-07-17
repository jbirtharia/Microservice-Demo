FROM openjdk:11-jre-slim-buster
ADD target/spring-cloud-netflix-eureka-server-0.0.1-SNAPSHOT.jar spring-cloud-netflix-eureka-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /spring-cloud-netflix-eureka-server-0.0.1-SNAPSHOT.jar"]