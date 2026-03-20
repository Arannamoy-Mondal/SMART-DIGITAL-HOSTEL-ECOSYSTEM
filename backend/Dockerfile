
FROM maven:3.9-ibm-semeru-25-noble AS stage1
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests


FROM eclipse-temurin:25.0.2_10-jdk-ubi10-minimal
WORKDIR /app
COPY --from=stage1 /home/app/target/*.jar app.jar
EXPOSE 8001
CMD [ "java","-jar","app.jar" ]