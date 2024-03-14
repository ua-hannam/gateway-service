FROM amazoncorretto:17-alpine
VOLUME /tmp
COPY build/libs/*.jar gatewayService.jar
ENTRYPOINT ["java", "-jar", "gatewayService.jar"]
