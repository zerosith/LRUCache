# LRU Cache Generic simple implementation

This repository contains an implementation od LRU cache

## Pre requisites
- Maven 3.8.3
- JDK 17.0.1

## Features
- Uses A LinkedList to store the order of the cache
- Uses A hasmap to easily know wheter an element is or not in the cache

## Run the code
Go to root folder LRUCache and run:
```sh
mvn clean install
```
After that the jar `LRUCache-0.1.jar` is generated in the **LRUCache\target** folder
Go to the folder **LRUCache\target** and run:
```sh
java -jar LRUCache-0.1.jar
```
This will run the tests included in this library