End to end projects includes:
(1) Flight reservation web project
(2) Flight checkin api project
(3) User angular as fornt end to consume Flight Checkin API
(4) Clinicals API project
(5) Use React as front end to consume clinicals api 
(6) Location web project
(7) Other utilities project include mail, pie chart reports, document upload, pdf generator, document upload/download.

				
										"E2E Projects"
										

Microservices?
---------------
> Heterogenous: different service can be built using different technologies
> Robustness: 
> Scalable
> Easy to deploy
> Replaceable and Reusable


Four Layes in J2EE
-------------------
> Data Access Layer
> Service Layer
> Integration Layer
> Presentation Layer





REST:
-----
HTTP Verbs - POST, GET, PUT, DELETE, HTTP Nouns - Resource URI

Key Principles:
	- Single HTTP Interface
	- Easy to access
	- Multiple representation (json, xml, plain text) - Using HTTP mime type






#########################################
	MY SQL Configuration
#########################################

My SQL Database:
----------------
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



MySqlSh: Project location-web
--------------------------------
> \sql
> \connect dbuser@localhost:3306
> use mylocaldb;
> create table location (id int PRIMARY KEY, code varchar(20), name varchar(20), type varchar(20))
> show tables;
> show columns from location;

> drop table location;



alerts.dmodi@gmail.com


Flight Reservation Project:
----------------------------

Docker Run:
-----------
docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=RESERVATION -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysqlflightdb --volume mysql-database-volume:/var/lib/mysqldb  mysql:5.7

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



Clinicals-api Project:
----------------------------

Docker Run:
-----------
docker volume create mysql-clinicals-data

docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=clinicals -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysql-clinicaldb --volume mysql-clinicals-data:/var/lib/mysqldb  mysql:5.7

Access Using MySQLSh:
--------------------
> \connect dbuser@localhost:3306
> \sql
> use clinicals;
> show tables;


Direct Usage of bash mySQL:
------------------------
(1) docker exec -it <<containerName>> bash
	$ docker exec -it clinicals bash
(2) Using login: 
	/# mysql -u<<rootuser>> -p (mysql -udbuser -p)
	provide password.

(3) Bulk update, or running sql script against the my sql db.
 	$ docker exec -i <<containerName>> mysql -u<<rootusername>> -password <<databaseName>> <db.sql (make sure less than symbol before sql file"<")
	example:
	docker exec -i mysql-clinicaldb mysql -udbuser -dbtest clinicals <db.sql
	


document-upload-web Project:
----------------------------
Docker Run:
-----------
docker volume create mysql-docupload-volume

docker run -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=dadmin -e MYSQL_DATABASE=documentdb -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbtest --name mysql-docupload --volume mysql-docupload-volume:/var/lib/mysqldb  mysql:5.7

Access Using MySQLSh:
--------------------
> \connect dbuser@localhost:3306
> \sql
> use documentdb;
> show tables;










##################################################################################
		Brief Usage Of Angular JS - Single Page App, for Flight Check-In
###################################################################################

- It is single page application development framework. The page make call to back-end systems

- Components: that are responsible for rendering the UI. 
	* It is combination of type-script internally convert to java script, html, stylesheet, etc..

- Services: that are responsible to make external call. To fetch the data. That data are being used
 			by the component to render the UI.

- Directives: In built to Angular which are used on HTML element. Special instructiions which are used
	 		during rendering the page.

- Binding: Angular allows to bind HTML element to Java Script easily

- Routing: It is very important part of any single page application framework. Angular provides very easy
			Routing module which can be used.



Install Angular:
----------------
- go to angular.io/start
- Download Node Packaging Manager (npm): https://nodejs.org/en/download/
- Install npm
- Validate: $ npm -v 
- Install Angular CLI: $ npm install -g @angular/cli
------------------



Steps:
#######
	(1) Create Projects:
		- It is being done using CLI cd /Users/omsairam/Documents/ddrive/training/E2E_BT/StudentDAL
		- $ ng new checkinapp
		
		- validate: cd <<project_name>>
		- $ ng serve -o (compile the project and open the project in ui: http://localhost:4200)


	
	






Angular Project Structure:
##########################

Important Files and Structure that make up Angular Project:

(1) Package.json - It includes all the dependencies that are needed in projects. In future any external dependencies or module can be added through CLI and this file can be updated automatically.

(2) src - folder where our source code resides 
(3) src -> App folder: All the component and services are stored
	Files: 
		app.components.css, app.components.html, 
		app.components.spec.ts (test file), app.components.ts (type script file) 
		-> These above files are components that made up the project.
		
		app.module.ts (it is like web.xml, where configuration are placed for the project)
		
	
(4) index.html -> It is the starting point for all the application, include app-root, which is also defined as selector in app.component.ts (root class file.).


(5) Opening internal terminal -> in visual studio 
	
	Create new component: command for that "ng generate component"
	Create new service: command for that "ng generate service"
	
	(A) startcheckin component:
			$ ng g c components/startcheckin
		
		checkin component:
			$ ng g c components/checkin
		
		confirmcheckin component:
			$ ng g c components/confirmcheckin
	
	(B) data services
			$ ng g s services/data
	
	Note: Both full form of generate/g, component/c, service/s can be used in command.
	
(6) Backend calls from the services:

	- import the module/packages from angular core, to make http call and if any response.
	- import map - method available in rxjs library
	- import observable to handle errors
	
	Issue: 
	*********
	can not find module @angular/http
	Run/install followings: $npm i @angular/http
	
	Create functions to call rest service: using http get and post method.
	
(7) Clear the component.html, start developing application needed html.

(8) Create route: create file under app called routing.module.ts
	- Define routes Array
	- Map Routes to components
	- Configure routing module.
	
(9) Wire whole application under - app.module.ts
	- Define all components under declarations
	- Define all the imports like http, html forms, our routes
	- Define providers, all the services are defined here, in our case backend restful service only
	- bootstrap: Starting point for the appliaction.
	
(10) At html: link the path once button click:

	


Note: 
*******
- Angular is a full-fledged mobile and web development framework. React is a framework only for UI development, which can be turned into a full-fledged solution with the help of additional libraries. React seems simpler at first sight, and it takes less time to start working on a React project.

- Architecture. Both Angular and React have component-based architecture, which means they have cohesive, reusable and modular components. But, the difference comes at the point of tech stack. React uses JavaScript while Angular goes with Typescript for web development which is more compact and error-free.

- Angular is a complete framework while React is a JavaScript Library. Angular uses a two-directional data flow process where it updates the Real DOM directly while React updates only the Virtual DOM and is concerned with the one-directional data flow.


#########################################################################################################
		Brief Usage Of React JS - Single Page App Framework (SPA Framework), For Clinical Services API:
#########################################################################################################

- One of the most popular, and growing as single page application framework (SPA)
- Introduced by Facebook, Reason for popularity is solving below issues in traditional 

	> Automatic UI State Management: 
		- Keeping track of the UI and managing state is hard and time consuming
		- It doesnt matter what series of steps users may have taken to change the UI, but what matter is 
		   where the ui ended up.
		- React will figure out what needs to be updated and it will do it for application. So no need to worry 
		about state management anymore.
		
	> Lightning fast DOM manipulation:
		- In traditional single page applications DOM manipulation is time consuming, meaning updating the DOM.
		- React provide/creates virtual DOM, which is faster to update, then react will update original DOM as and when 
			needed.
		- It will track/figure out the difference between virtual DOM and original, make the least amount neccessary changes 
		to make original DOM  to keep UI in synch. this process called reconciliation.
		
	> Components: 
		- React recommends to break UI into smaller chunk or component. React provides API's to create component.
		- Using APIs, we can combine components to create complex component.
		
	> View through JavaScript:
		- Views are entirely defined under java script.
		- HTML like syntax are direcly used under java script. These syntax are called JSX (java script extension)
	
	> It is View in MVC.
	
	
	
NodeJS:
******
- NodeJS is open source javascript runtime which allow js to run outside of the browser.
- It also provide NPM (node packaging manager), usin that more js library can install like express, react, etc..


		
		
Steps:
#######
First install react create app package/CLI to create react project:
$ npm i create-react-app

if error occured:
	npm WARN saveError ENOENT: no such file or directory, open '/Users/omsairam/package.json'
	npm WARN enoent ENOENT: no such file or directory, open '/Users/omsairam/package.json'
	
Solution:
	use below command: npm install -g create-react-app
	
	(1) Create Projects:
		- It is being done using CLI cd /Users/omsairam/Documents/ddrive/training/E2E_BT/StudentDAL/ReactApp
		- $ create-react-app clinicalsapp
		- validate the clinicalsapp folder is created under ReactApp
	
	(2) cd /clinicalsapp
		> Install DOM router module: to route/navigate from page to page
			$ npm install --save react-router-dom
			
		> install axios library: to make back end ajax call or restfull API calls
			$ npm install --save axios
			
	(3) Create component package and various component which are required for the project.
		Home.js, AddPatient.js, AnalyzeData.js, ChartGenerator.js, CollectClinicals.js.
	
	(4) Configuring Routing: to navigate/route from one UI to another
		- Index.js -> Include browser router, wrap arround <App>
		- App.js -> Using switch configure route/uri
		
	(5) Retrieve all the patients from backend restful service call: Home.js
		- componentWillMount() - Use this lifecycle method of the axios library
		- Display data with links, import react libraries to do it.
		
		to test: in terminal write: 
			$ yarn start 
			$ npm start
			
			
	(6) Add new patient: AddPatient.js
		- Use event to preventDefault, so browser does not submit the form at the handleSubmit function. In this case it is
		  Ajax call so, do not want browser to submit the form.
		- Also using the event.target.value at onChange property, so that different/new entered value to the input will 
		  be considered in handleSubmit function.
		- Tostify Library: install react tostify
			$ npm i react-toastify
		
		
		to test: in terminal write: 
			$ yarn start 
			$ npm start
		
		
	(7) Update patient records: CollectClinicals
		
	
		
		










































