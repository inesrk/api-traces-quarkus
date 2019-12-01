######
## STAGE 1: build with maven builder image with native capabilities
######

# Use a base image with a Redhat customized Centos based image with GraalVM
FROM quay.io/quarkus/centos-quarkus-maven:19.2.1 AS build

# Use a standard work directory
WORKDIR /usr/src/app

# Do all actions as super user (life is easy 👼)
USER root

# Copy maven + pom.xml, optimization for caching and speed-up this step
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Install the maven dependencies
RUN ./mvnw clean install

# Copy source folder, they will be used to build app from sources (see .dockerignore file specs)
COPY . .

# Build the app
RUN ./mvnw clean package -Pnative

######
## STAGE 2: create the docker final image
######

# Use a base image with Redhat UBI
FROM registry.access.redhat.com/ubi8/ubi-minimal

# Use a standard work directory
WORKDIR /usr/src/app

# Bundle app source
COPY --from=build /usr/src/app/target/*-runner ./application

# Define port 8080 as standard server output
EXPOSE 8080

# Default command as container start
CMD ["./application", "-Dquarkus.http.host=0.0.0.0", "-Dquarkus.http.port=8080"]
