# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Grant execute permissions to the Maven wrapper
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean install

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
CMD ["./mvnw", "spring-boot:run"]
