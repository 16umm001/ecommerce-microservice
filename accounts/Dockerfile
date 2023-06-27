FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/accounts-0.0.1.jar /app

EXPOSE 8081

COPY ./entrypoint.sh /entrypoint
RUN sed -i 's/\r$//g' /entrypoint
RUN chmod +x /entrypoint

RUN chmod +x /entrypoint

ENTRYPOINT ["/entrypoint"]