#########################################
	MY SQL Configuration
#########################################

My SQL Database: Docker Image
------------------------------------
docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=mylocaldb -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysqldb --volume mysql-database-volume:/var/lib/mysqldb  mysql:5.7


MySqlSh: Project StudentDAL
--------------------------------
> \sql
> \connect dbuser@localhost:3306
> use mylocaldb;
> create table studentdb(id int PRIMARY KEY AUTO_INCREMENT, sname varchar(20), scourse varchar(30), sfee int)
> show tables;
> show columns from studentdb;

> drop table studentdb;



MySqlSh: Project location
--------------------------------
> \sql
> \connect dbuser@localhost:3306
> use mylocaldb;
> create table location (id int PRIMARY KEY, code varchar(20), name varchar(20), type varchar(20))
> show tables;
> show columns from location;

> drop table location;




WEB MVC project url: http://localhost:8080/location/showCreate
Intergration Layer: http://localhost:8080/location/locations