# Syllabus

In this course, we will start from Java language fundamentals and work our way up from basic console based program to real world application with REST API.
Throughout the course, we will not only focus on how to write programmes, but also on how to test them.

In week 1, you will learn Java programming language fundamentals.
In week 2, you will learn how to create working Java programs and will be introduced to Spring Boot application framework.
In week 3, you will apply skills acquired in previous weeks to build an actual application.

## Week 1
Introduction to development in Java

### Lesson 1
- Brief history of Java (10 min)
- Where Java fits in on the developer landscape (10 min)
- Java basics (1h)
  - Basic Java language syntax
  - Java Data Types
  - Operators
  - Conditionals 
  - Arrays
  - Scope
- Introduction to IDE (30 min)
- Running you first Java program (console) (10 min)
- Practice:
  - learn to work with strings
    - string concatenation
    - string builder
    - split
  - learn to work with arrays
    - loops

### Lesson 2
 - Java basics (continued) (1h)
   - Objects and classes, fields, constructors
   - Functions / methods, lambda
   - Scope
   - Access modifiers
   - Errors and exception handling
   - Records and immutability
 - How to read documentation (JavaDoc)
 - Practice:
   - prepare for next lessons

### Lesson 3
- Dependency management (Gradle, Maven) (30 min)
- Testing your code (1h)
    - Testing fundamentals
    - Unit testing
    - Integration testing
    - Test Driven Development
    - Code coverage
    - Code quality
- Annotations
- JUnit (1h)
- Refactoring your code for testability
- Logging
- Debugging
- Practice
- Homework: Unit test homework from previous lesson?

### Lesson 4
- Java basics (continued) (1h)
  - Programming language and communication of intent
  - Polymorphism
  - Inheritance
- Practice
- Homework

### Lesson 5
- Java Wrapper Classes
- Lists, Stacks, Sets, Maps, Queues, Utility classes
- Functional Interfaces and Stream API
- Practice
- Homework

## Week 2
Application design

Probably some repetition in the beginning

### Lesson 6
- Homework review
- Java libraries
  - java.time
- Reading from command line
- Java Optionals
- Practice: 
  - implement simple console application
- Homework: working with Java Time

### Lesson 7
- Homework review
- Java libraries
  - java.io.File, java.nio.Path, InputStream and OutputStream
- Practice
- Homework: read and write to file

### Lesson 8
- Homework review
- JRE, JDK and JVM (30 min)
  - Java language, Java compiler and Java bytecode (quick overview)
- Multithreading
- Memory management
- Practice
  - maybe some bad code optimization (memory, thread safety)


### Lesson 9
- The SOLID principle (20 min)
- Object-Oriented Design patterns (20 min)
- Inversion of Control (IoC) (20 min)
- Generics (30 min)

- I/mutability, state and functional programming principles, Functional Interfaces?
- Lambda expressions
- Optionals?

- Practice
  - Generics
  - IoC
- Homework


### Lesson 10
- Application Programming Interface (API) (30 min)
  - What is an API
  - Types of APIs
  - How to use an API
- Design of a REST API (60 min)
  - Resources, methods, parameters
  - Request and response (Serialization)
  - Best practices
- Reflection (60 min)
  - What is reflection
  - How to use reflection
  - When to use reflection
- Practice
  - Reflection
- Homework
  - Try working with an API (PostMan or similar)

## Week 3
Project: Working on an application

Introduction to the project
Split the project into smaller increments


### Lesson 11
- Java Application Development and Frameworks (10 min)
- Introduction to Spring Boot framework (30 min)
  - Running first Spring Boot application (10 min)
  - MVC and IoC in Spring Boot (20 min)
- Basic MVC model overview (30 min)
  - Controller
  - Service
  - Repository
- Testing (20 min)
- Practice
  - Starting the Spring Boot application
- Homework
  - Design REST API for the project

### Lesson 12
- Homework review
- Project work:
  - Implementing REST API
  - Implementing the service layer
  - Testing (60 min)

### Lesson 13
- Project work:
  - Connecting to repositories (60 min)
- Application configuration
- Securing your API
- Introduction to the Project

## Lesson 14
- Microservice architecture (Spring Cloud)
- Asynchronous execution
- Event based architecture
- Reactive Streams (Spring WebFlux)
- Consuming to 3rd party APIs
- Caching
- Working on the project

## Lesson 15
- Project presentation and review
- Running your application in production environment
- Monitoring
