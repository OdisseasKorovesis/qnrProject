# My Assessment Project

Application developed using Java 8, the Spring Framework and MySQL database, for assessment purposes.

## Description

The entire project was implemented as a Spring Boot (2.4.2) application based on a MySQL database. Unit testing was done
using JUnit5 and Mockito.

Application is separated in four subsystems:

Database Subsystem

* Purpose was to create a MySQL database based on a given schema
* Creation of the database was done through hibernate after creating all the necessary entities within the Spring application.

Info cataloguing subsystem

* Purpose was to retrieve lists of information from the created database and display it in a website.
* Implementation was done using the Spring MVC model as well as thymeleaf templates.

Web Services Subsystem

* Purpose was to create REST Web Services able to provide lists of information from the database as XML responses.
* Implementation was done using Spring REST controllers and Jackson to map the responses to XML.

Web Service Client Subsystem

* Purpose was to provide a way to access the endpoints created in WS subsystem.
* Implementation was done using HTML pages that through jQuery AJAX calls to the created endpoints are filled with the required information.

## Getting Started

### Installing

* Clone this github repository to any PC.
* Open cloned project with any Java-compatible IDE.

### Executing program

To test the Database Subsystem:

* Navigate to myQnrProject\src\main\resources\application.properties and enter the required info (url/username/password) for connection to your local MySQL database.
* Run main method of project through MyQnrProjectApplication class, application will automatically generate the schema, the tables and fill them with some sample data.

To test the Info Cataloguing subsystem:

* Navigate to myQnrProject\src\main\resources\application.properties and set the spring.profiles.active to "nonrest".
* Run main method of project through MyQnrProjectApplication class
* Open up any of your browsers to the index page (localhost:8080/) and from there you can navigate to all the required information.

To test the WebServices subsystem:

* Navigate to myQnrProject\src\main\resources\application.properties and set the spring.profiles.active to "rest".
* Run main method of project through MyQnrProjectApplication class
* Navigate to myQnrProject\postman-collection\QnRProject.postman_collection.json and add this postman request collection to your Postman.
* Execute the requests by order to view the all the necessary information in XML form.

To test the WebServices Client subsystem:

* Navigate to myQnrProject\src\main\resources\application.properties and set the spring.profiles.active to "rest".
* Run main method of project through MyQnrProjectApplication class
* Open up any of your browsers to the page (localhost:8080/wsclientindex) and from there you can navigate to all the required information.

## Authors

ex. [Odisseas Korovesis-Danon](https://www.linkedin.com/in/odisseas-korovesis-danon/)

## Not Implemented Yet

* Fourth Subsystem (WS Client) has only index page with sample data, not possible to navigate and select which 
  info to view.
* Extensive documentation within the source code (comments, javadoc) has not been provided.