FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar gatewayService.jar
ENTRYPOINT ["java", "-jar", "gatewayService.jar"]
