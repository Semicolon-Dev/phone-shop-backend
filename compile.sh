#!/bin/sh

./mvnw clean package
docker build -t phone-shop .