# Salon Dayz in Java and Spark
by [Jeremy Fryd](https://github.com/jkontiki)

version 0.0.0000001, September 23, 2016

## Description
a post-garage surf rock project

### Specifications

| Behavior                   | Input              | Output             |
|----------------------------|--------------------|--------------------|
| Add a Stylist          | "Louis XIV"           | "Congratulations, you have added stylist Louis XIV!"           |
| Add a Client          | "the Duke of Buckingham"           | "Congratulations, you have added the Duke of Buckingham to your list of clients! Huzzah!"           |
| Add Client to specific Stylist          | "[check]Louis XIV"           | "Louis XIV  -the Duke of Buckingham"           |
| Delete a Client          | "Delete the Duke of Buckingham"           | "Congratulations, you have deleted the Duke of Buckingham to your list of clients!"           |
| Delete a Stylist          | "Delete Louis XIV"           | "Congratulations, you have deleted stylist Louis XIV!"           |


### Database Commands

In PSQL:
CREATE DATABASE hair_salon;

In bash:
$ psql media < media.sql

## Known Issues
n/a

## Technology Used
Java, Spark, JUnit, Gradle, Oculus Rift

## Legal
Licensed under GNU 3.0

Copyright (c) 2016 _Jeremy Fryd_ All Rights Reserved.
