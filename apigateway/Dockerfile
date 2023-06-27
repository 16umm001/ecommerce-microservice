FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/apigateway-0.0.1.jar /app

EXPOSE 8084

CMD ["java", "-jar", "apigateway-0.0.1.jar"]