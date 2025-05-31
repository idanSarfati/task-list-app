# Use official Maven image with JDK 17
FROM maven:3.9.6-eclipse-temurin-17

# Set the working directory inside the container
WORKDIR /app

# Copy everything from your repo into the container
COPY . .

# Build the app
RUN ./mvnw clean install

# Run the app
CMD ["java", "-jar", "target/todoapp-0.0.1-SNAPSHOT.jar"]
