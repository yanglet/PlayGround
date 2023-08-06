#!/bin/bash

docker-compose down
docker rmi ranking_app:latest
docker rmi ranking-app:latest
rm -rf ./init-db/data/*
chmod +x ./gradlew
./gradlew clean bootjar
docker-compose build
docker-compose up -d