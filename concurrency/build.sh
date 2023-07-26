#!/bin/bash

docker-compose down
docker rmi concurrency_app:latest
docker rmi concurrency-app:latest
chmod +x ./gradlew
./gradlew clean bootjar
docker-compose build
docker-compose up -d