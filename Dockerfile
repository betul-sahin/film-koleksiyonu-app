FROM openjdk:15-jdk-alpine

VOLUME /tmp

ADD target/film-koleksiyonu-app-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch film-koleksiyonu-app-0.0.1-SNAPSHOT.jar'

EXPOSE 8081

CMD ["java", "-jar", "film-koleksiyonu-app-0.0.1-SNAPSHOT.jar"]