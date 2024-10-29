FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build artifact into the container
COPY target/url-shortening-service-0.0.1-SNAPSHOT.jar url-shortening-service.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "url-shortening-service.jar"]
