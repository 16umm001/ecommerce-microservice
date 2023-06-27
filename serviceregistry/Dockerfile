FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/serviceregistry-0.0.1.jar /app

EXPOSE 8761

CMD ["java", "-jar", "serviceregistry-0.0.1.jar"]