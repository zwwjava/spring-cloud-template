FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar yifei-chief.jar

EXPOSE 9002

ENTRYPOINT ["java", "-jar", "/yifei-chief.jar"]