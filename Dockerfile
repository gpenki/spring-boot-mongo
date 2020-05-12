FROM openjdk:8-alpine

# Required for starting application up.
RUN apk update && apk add /bin/sh

RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app

COPY target/spring-boot-mongo-0.0.1-SNAPSHOT.jar $PROJECT_HOME/spring-boot-mongo.jar

WORKDIR $PROJECT_HOME

#CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongo:27017/spring-mongo","-Djava.security.egd=file:/dev/./urandom","-jar","./spring-boot-mongo.jar"]
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://18.222.205.82:27017/test","-Djava.security.egd=file:/dev/./urandom","-jar","./spring-boot-mongo.jar"]