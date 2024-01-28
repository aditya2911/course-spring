FROM eclipse-temurin:17


RUN apt-get update && apt-get install -y maven
RUN apt-get update && apt-get install -y maven netcat
WORKDIR /app

COPY . /app

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

RUN mvn clean package



EXPOSE 8080

CMD [ "java", "-jar", "target/course-spring-0.0.1-SNAPSHOT.jar"]
