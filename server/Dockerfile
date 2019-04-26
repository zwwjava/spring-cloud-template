FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar server.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/server.jar"]