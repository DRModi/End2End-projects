
Docker Run:
-----------
docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=RESERVATION -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysqlflightdb --volume mysql-database-volume:/var/lib/mysqldb  mysql:5.7


docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=RESERVATION -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysqlflightdbtest -v /Users/omsairam/docker_persistent_data/mysqldb-flightreservation-app mysql:5.7 

docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=RESERVATION -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysqlflightdbtest -v /Users/omsairam/docker_persistent_data/mysqldb-flightreservation-app:/var/lib/mysqldb mysql:5.7 

CREATE DATABASE RESERVATION

USE RESERVATION


CREATE TABLE user 
(
ID INT NOT NULL AUTO_INCREMENT,
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
EMAIL VARCHAR(20),
PASSWORD VARCHAR(256), 
PRIMARY KEY (ID),
UNIQUE KEY (EMAIL)
)

CREATE TABLE flight
(
  ID INT  NOT NULL AUTO_INCREMENT,
  FLIGHT_NUMBER VARCHAR(20)  NOT NULL, 
  OPERATING_AIRLINES VARCHAR(20)  NOT NULL,
  DEPARTURE_CITY VARCHAR(20)  NOT NULL,
  ARRIVAL_CITY VARCHAR(20)  NOT NULL,
  DATE_OF_DEPARTURE DATE  NOT NULL,
  ESTIMATED_DEPARTURE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
  PRIMARY KEY (ID)
)

CREATE TABLE passenger
(
  ID         INT NOT NULL AUTO_INCREMENT,
  FIRST_NAME       VARCHAR(256),
  LAST_NAME    VARCHAR(256),
  MIDDLE_NAME   VARCHAR(256),
  EMAIL VARCHAR(50),
  PHONE VARCHAR(10),
  PRIMARY KEY (ID)
)

CREATE TABLE reservation
(
  ID INT NOT NULL AUTO_INCREMENT,
  CHECKED_IN TINYINT(1),
  NUMBER_OF_BAGS INT,
  PASSENGER_ID INT,
  FLIGHT_ID INT,
  CREATED TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (ID),
  FOREIGN KEY (PASSENGER_ID) REFERENCES passenger(ID) ON DELETE CASCADE,
  FOREIGN KEY (FLIGHT_ID) REFERENCES flight(ID)
)




-- Adding user role table

use reservation

CREATE TABLE role 
(
ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(20),
PRIMARY KEY (ID)
)

create table user_role(
user_id int,
role_id int,
FOREIGN KEY (user_id)
REFERENCES user(id),
FOREIGN KEY (role_id)
REFERENCES role(id)
)

insert into role values(1,'ADMIN')

insert into user_role values(1,1);

select * from user

select * from role

select * from user_role

drop table role

drop table user_role







Access Using MySQLSh:
--------------------
> \connect dbuser@localhost:3306
> \sql
> use RESERVATION;
> show tables;
> show columns from user;
> show columns from passenger;
> show columns from flight;
> show columns from reservation;

SELECT * FROM user

SELECT * FROM passenger 

SELECT * FROM flight

SELECT * FROM reservation



DROP TABLE USER
DROP TABLE RESERVATION
DROP TABLE PASSENGER
DROP TABLE FLIGHT

DROP DATABASE RESERVATION












flights:
---------

insert into flight values(1,'AA1','American Airlines','AUS',
'NYC',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 03:14:07');

insert into flight values(2,'AA2','American Airlines','AUS',
'NYC',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 05:14:07');

insert into flight values(3,'AA3','American Airlines','AUS',
'NYC',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 06:14:07');

insert into flight values(4,'SW1','South West','AUS',
'NYC',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 07:14:07');

insert into flight values(5,'UA1','United Airlines','NYC',
'DAL',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 10:14:07');

insert into flight values(6,'UA1','United Airlines','NYC',
'DAL',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 10:14:07');

insert into flight values(7,'SW1','South West','AUS',
'NYC',STR_TO_DATE('02-06-2018', '%m-%d-%Y'),'2018-02-06 07:14:07');

insert into flight values(8,'SW2','South West','AUS',
'NYC',STR_TO_DATE('02-06-2018', '%m-%d-%Y'),'2018-02-06 08:14:07');


insert into flight values(9,'SW3','South West','NYC',
'DAL',STR_TO_DATE('02-06-2018', '%m-%d-%Y'),'2018-02-06 10:14:07');

insert into flight values(10,'UA1','United Airlines','NYC',
'DAL',STR_TO_DATE('02-06-2018', '%m-%d-%Y'),'2018-02-06 10:14:07');




