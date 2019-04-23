FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar order.jar

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "/order.jar"]