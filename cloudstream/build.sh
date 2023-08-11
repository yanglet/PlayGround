#!/bin/bash

docker-compose down

docker rmi cloudstream_pay-service:latest
docker rmi cloudstream-pay-service:latest
docker rmi cloudstream_order-service:latest
docker rmi cloudstream-order-service:latest
docker rmi cloudstream_item-service:latest
docker rmi cloudstream-item-service:latest

rm -rf ./init-db/item/data/*
rm -rf ./init-db/order/data/*

chmod +x ./gradlew
./gradlew clean bootjar

docker-compose build
docker-compose up -d