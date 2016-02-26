# Phone Book RESTful Web Service

An example implementation of a RESTful Web Service using Spring Boot, Java 8 and Swagger.

## How to run the service

This is a Spring Boot project built with Maven 3.3 and Java 8. So, after you clone it, you can follow [Spring Boot instructions](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html) and run the phone book with one of the following 3 ways:

* Type `mvn spring-boot:run` in your console, to use the Maven plugin.
* Build the project with `mvn install` and then run it as a packaged application with `java -jar target/restful.phonebook-1.0-SNAPSHOT.jar`.
* Load the project on your favourite IDE and run it from there. For Eclipse, this would be `mvn install eclipse:eclipse` to build the project, Import -> Existing Projects into Workspace and finally Run as Java Application on the Application.java file.

## What to expect to see

When you run the application a RESTful phone book will be exposed under the URI `http://localhost:8080/phonebook/contacts`. You can type this URI to your favourite browser to see the contacts that are already registered - these are  the four main characters from the Simpsons TV show, but don't rush to call them, I do not guarantee they will answer! Then, you can use curl or another tool of your preference to interact with the phone book.

## How to interact with the phone book

The project uses SpringFox to create a human readable specification for the phone book's API. This is accomplished using a combination of SpringFox classes and Swagger annotations. To take a look at the phone book's API you can type `http://localhost:8080/swagger-ui.html` on your browser. There you will find a short description of the project and an overview of the operations you can execute, using the Phone Book Controller. These operations are:

* Search all contacts by name, surname or phone. In case that there are not search criteria, all contacts will be returned.
* Retrieve a single specific contact.
* Create a new (not indexed) contact.
* Create an indexed contact or update an old one.
* Delete a single specific contact.

## A few words about the code

As in every Spring Boot project, you can start with the Application class which is used to run the project and also contains the Swagger configuration and description.

The class that hanldes the HTTP requests to the server is PhoneBookController, which makes use of Spring's RequestMapping annotations to correspond the requests to specific URIs to phone book actions and Swagger's ApiOperation and ApiParam to enrich the phone book API.

Under the dao package there is a PhoneBook interface describing the operations to the persistent layer and an implementation using an in-memory storage schema.

## Never forget Unit Testing

This was my first project I tried to write tests and codes in parallel, following the principle "write the tests first and when your code passes the tests, you can continue" and it was actually pretty fun! In the ServerSideTests there are tests for every phone book's operation.
