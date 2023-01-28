# Trip Management System


This project is developed by a team of 4 members with the help of the Spring-Boot framework.It is Back-End project build in construct week at masai.
It contained all the basic CRUD operation related to different entity.

## Contributors
@Shubhamrathore09


## Tech Stack and Tools
- Java
- Spring Boot Framework
- Spring Data JPA
- Spring MVC
- Hibernate
- MySQL
- Swagger
- Lombok


## Modules
- Admin Module
- Customer Module
- Login/Logout Module
- Booking Module
- Ticket Module
- Bus Module
- Travels Module
- Route Module

## Features
Admin Features:
 - Admin can perform CRUD operation like add, update and delete by using generated session key.
 - Admin can add Bus,Route,Hotel,and perform some of operation

 
Customer Features:
 - Customer can Ragistor him self.
 - Customer can see the routes and see the available buses and see the travels details
 - Customer can book the hotel,Bus,and can give feedback
  
## Installation & Run
- To run this API server, you should update the database configuration inside the application.properties file which is present in the src/main/resources folder.
- Update the port number, username and password as per your local database configuration.
server.port=8008
spring.datasource.url=jdbc:mysql://localhost:3306/Trim;
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=enter_username
spring.datasource.password=enter_password
```
## API Root Endpoint
```
https://localhost:8888/
```
