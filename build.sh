#!/usr/bin/env bash
cd Exam1/
mvn clean assembly:assembly
cd ..
cp Exam1/target/Exam1-0.0.1-SNAPSHOT-jar-with-dependencies.jar docker/java/app.jar
docker build -t mysql-sakila:0.0.1 docker/mysql
docker build -t java-sakila:0.0.1 docker/java