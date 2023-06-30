

FROM openjdk:8-jdk
WORKDIR /app
COPY target/spring-spyglass-planner-0.0.1-SNAPSHOT.jar spring-docker.jar
COPY /honeycomb-opentelemetry-javaagent-1.5.0.jar honeycomb.jar
ENV SERVICE_NAME=spyglass
ENV HONEYCOMB_API_KEY=hgStbdzajjDRfNtwrhNkgG
ENV HONEYCOMB_METRICS_DATASET=spyglass-metrics
EXPOSE 8080
CMD ["java", "-javaagent:honeycomb.jar", "-jar", "spring-docker.jar"]