# Wildlife Tracker

#### _An application to manage track wildlife, 9/29/2016_

#### By _**Timothy Herold**_

## Description

_This application is designed to the Forest Service. This application will allow the rangers of the Forest Service to track endangered and non-endangered animals for a wildlife study._

## Setup/Installation Requirements

_Create the database and tables in PSQL:_
* _CREATE DATABASE wildlife_tracker;_
* _CREATE TABLE animals (id serial PRIMARY KEY, name varchar NOT NULL UNIQUE, health varchar, age numeric, type varchar NOT NULL);_
* _CREATE TABLE rangers (id serial PRIMARY KEY, username varchar NOT NULL UNIQUE, firstname varchar NOT NULL, lastname varchar NOT NULL, badge int NOT NULL, phone bigint NOT NULL);_
* _CREATE TABLE locations (id serial PRIMARY KEY, name varchar NOT NULL UNIQUE, x_coord numeric NOT NULL, y_coord numeric NOT NULL);_
* _CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int REFERENCES animals (id) ON DELETE CASCADE, location_id int REFERENCES locations (id) ON DELETE CASCADE, ranger_id int REFERENCES rangers (id) ON DELETE CASCADE);_

_Download Java and build source code from [GitHub](https://github.com/therold/wildlife-tracker) in the terminal._
* _git clone https://github.com/therold/wildlife-tracker.git_
* _cd wildlife-tracker_
* _gradle run_

_Then open [http://localhost:4567](http://localhost:4567) in your preferred web browser._

## Objectives

* _An interface or inherited class provides similar content in different classes._
* _Database timestamps are included for each sighting._
* _At least two Exceptions can be thrown and caught._
* _Constants are used in at least one class._
* _Previous standards are met (see below)._
* _Project demonstrates an understanding of concepts covered this week. If prompted, you can discuss your code and concepts behind it with an instructor using the correct terminology._
* _Project is in a polished, portfolio-ready state._
* _Application works as expected._
* _Tests have complete coverage for all behaviors._
* _All tests are formatted correctly and pass._
* _Logic is easy to understand._
* _Build files are discluded from Git using a .gitignore file._
* _Code and Git documentation follows best practices (descriptive variables names, proper indentation and spacing, separation between front and back-end logic, detailed commit messages in the correct tense, and a well-formatted README)._

## Minimum Specification
* The program should be able to store and retrieve the names of each animal.
  * **Example Input**: squirrel
  * **Example Output**: squirrel
* The program should be able to store and retrieve the health of each endangered animal.
  * **Example Input**: panda health is ill
  * **Example Output**: panda health is ill
* The program should be able to store and retrieve the age of each endangered animal.
  * **Example Input**: panda age is newborn
  * **Example Output**: panda age is newborn
* The program should be able to store and retrieve the location where an animal was sighted.
  * **Example Input**: north of bridge
  * **Example Output**: north of bridge
* The program should be able to store and retrieve the name of the ranger who saw the animal
  * **Example Input**: Bob
  * **Example Output**: Bob
* The program should be able save all of the above to a database.
  * **Example Input**: Save Bob
  * **Example Output**: [None]
* The program should be able delete any of the above from the database.
  * **Example Input**: Delete Bob
  * **Example Output**: [None]

## Technologies Used

* _Java_
* _JUnit_
* _Gradle_
* _Spark_
* _Velocity_
* _PostgreSQL_

### License

*GPL*

Copyright (c) 2016 **_Timothy Herold_**
