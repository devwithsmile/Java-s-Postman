# Start with a base image containing Java runtime
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/bookstore-0.0.1-SNAPSHOT.jar /app/bookstore.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file 
CMD ["java", "-jar", "bookstore.jar"]