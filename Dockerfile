##################################
# Docker image to compile
##################################
FROM maven:3.9.5- as builder

# Working directory
WORKDIR /project

# Copy dependencies
COPY pom.xml /project/

# Download project dependencies
RUN mvn clean verify

# Copy project code
COPY /src /project/src

# Compile project
RUN mvn package -o -DskipTests=true

#################################################
# Docker base image for app container
#################################################
FROM openjdk:21

# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/app/

# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/app/

# Indica el puerto que expone el contenedor
EXPOSE 8080

# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "app.jar" ]
