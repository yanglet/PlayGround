#!/bin/bash

docker-compose down

docker rmi outbox_example-app:latest
docker rmi outbox-example-app:latest

chmod +x ./gradlew
./gradlew clean bootjar

docker-compose build
docker-compose up -d