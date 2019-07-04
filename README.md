Project Description
-------------------------
Rest API implementation consist of following feature :

1. GET: accounts -> list of user accounts
2. GET: transactions -> list transaction associated for given account and provide reference links to go back to accounts api

Assumptions:

1. User id is required to pass in request to get all account of that user.
2. Transactions list will be fetched by account number, so account number is unique and has to be passed in get transaction api /


Frameworks and tools :
---------------------
Project is developed using Spring boot and spring data rest frameworks.
Following frameworks and tools used for development.

Java 1.8
Spring Boot
DB: H2 -in memory
Junit
Mockito
Sla4j
Maven
IDE : Intellij
Test coverage : jacoco
Test tool : postman


Build and Run
-------------------

1. Build using maven command :  mvn clean package

2. Project can be run locally  in IDE by running  spring boot main application class : AccountApiApplication.java

3. To run from terminal : Run command "mvn spring-boot:run"


Testing
---------------------------------------------------------------------------

Swagger URL : http://localhost:8080/swagger-ui.html

Local Tests : Junit and integration tests are executed using maven test command or mvn test goal in IDE
    >mvn test / mvn clean install/ mvn clean package

Code Coverage : code coverage jacoco report is generated in target directory @/project-root/target/jacoco/index.html

Sample responses can be found @/src/test/resources

Database
---------
In memory database H2 can be accessed while application is running following URL.

    DB console URL : http://localhost:8080/console


APIs:
==========================
1. Get accounts :

    API url    : http://localhost:8080/api/v1/accounts/{userId}
    sample url : http://localhost:8080/api/v1/accounts/u001

    url with paging params :
    test url :http://localhost:8080/api/v1/accounts/u001?page=0&size=20


2. Get transactions :

    API url          : http://localhost:8080/api/v1/accounts/{accountNumber}/transactions
    sample url       : http://localhost:8080/api/v1/accounts/a10001/transactions

    url with paging params :
    test url :http://localhost:8080/api/v1/accounts/a10001/transactions?page=0&size=20

Notes
------------------------
In memory database is created during application startup and cleaned up on application shutdown.
For testing purpose, test data is loaded in database using JPA repository operations in com/anz/account/bootstrap/ApplicationInitializer.java.
ApplicationInitializer.java is not required in production.
