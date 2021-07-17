FROM openjdk:11-jre-slim-buster
ADD target/cloud-configuration-server-0.0.1-SNAPSHOT.jar cloud-configuration-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar /cloud-configuration-server-0.0.1-SNAPSHOT.jar"]