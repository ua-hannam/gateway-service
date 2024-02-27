FROM amazoncorretto:17-alpine

ARG JAR_FILE=build/libs/gateway-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} gatewayService.jar

CMD ["java", "-jar", "-Duser.timezone=Asia/Seoul", "gatewayService.jar"]
