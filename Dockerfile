FROM openjdk:11
COPY target/phone-shop.jar phone-shop.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=postgres", "/phone-shop.jar"]