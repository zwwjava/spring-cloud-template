FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar api_gateway.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "/api_gateway.jar"]