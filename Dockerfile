FROM openjdk:8
ADD target/mini-crm.jar mini-crm.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mini-crm.jar"]