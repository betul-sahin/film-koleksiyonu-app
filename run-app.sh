#!/usr/bin/env bash
mvn clean package -DskipTests

docker build -t betulsahinn/film-koleksiyonu-app .
docker run -p 8081:8081 -t betulsahinn/film-koleksiyonu-app