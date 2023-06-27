FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/id.generator-0.0.1.jar /app

EXPOSE 8082

CMD ["java", "-jar", "id.generator-0.0.1.jar"]