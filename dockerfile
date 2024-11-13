# Build stage
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /app

# Copy project files into the container
COPY .mvn/ .mvn
COPY src/ src
COPY pom.xml .
COPY mvnw .

# Make the Maven wrapper executable
RUN chmod 775 ./mvnw

RUN ls -l /app
# Package the application
RUN ./mvnw package -f ./pom.xml

# Extract layers using layertools from the packaged JAR
RUN java -Djarmode=layertools -jar ./target/spring-1.0.0.jar extract

# Runtime stage
FROM eclipse-temurin:17-jre-alpine as runtime
WORKDIR /app

# Copy each layer from the builder stage
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Expose the port the app runs on
EXPOSE 8083

# Use the correct entrypoint for the Spring Boot JAR
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
