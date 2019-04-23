FROM hub.c.163.com/library/java:8-alpine

ADD target/*.jar product.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "/product.jar"]