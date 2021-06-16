# DevBoard

DevBoard is a Java desktop application that connects developers to project owners. A project owner may be a company, start-up, or any person with a software project they need completed. DevBoard allows developers to find side projects to build their experience and get paid for doing so.

## Architecture

This application was built using Java and runs on desktop. The frontend was built using JavaFX, and the backend database is hosted in Google Firebase. The database is accessed via the Firebase REST API.


## Design Patterns

This application was designed around Entity-Controller-Boundary design pattern, which can be seen in the directory structure of the repository. The Entity classes define the core data classes, the controller classes define our business logic, and the boundary classes define the our user-interface.

Some other design patterns/strategies that are used include Singleton and Dependency-Injection.

## Continous Integration

This application uses TravisCI to manage automated builds, and uses SonarLint to handle automated code linting.

## Technical Highlights

This project incorporates of number of technical features including

 - Multithreading
 - Java generic types
 - REST API calls
