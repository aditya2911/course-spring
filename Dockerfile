FROM eclipse-temurin:21


RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY . /app
RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/course-spring-0.0.1-SNAPSHOT.jar"]
