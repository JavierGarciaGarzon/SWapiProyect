FROM openjdk:17-jdk-slim
COPY target/swapi-0.0.1-SNAPSHOT.jar swapi-proxy.jar
ENTRYPOINT ["java","-jar","/swapi-proxy.jar"]
EXPOSE 8080