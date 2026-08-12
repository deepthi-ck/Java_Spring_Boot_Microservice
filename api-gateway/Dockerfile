FROM eclipse-temurin:11-jre
WORKDIR /app
COPY target/api-gateway-*.jar app.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/app/app.jar"]