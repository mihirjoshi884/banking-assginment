# banking-assginment

Welcome to the Banking Web Application documentation. This document provides an overview of the application, including its features, setup instructions, and usage guidelines.

## Table of Contents

1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Project Structure](#project-structure)
4. [Configuration](#configuration)
5. [Database Setup](#database-setup)
6. [Running the Application](#running-the-application)
7. [Web Controller](#web-controller)
8. [JSP Views](#jsp-views)
9. [Session Management](#session-management)

## 1. Overview

The Banking Web Application allows users to manage their bank accounts, account statements, and transactions. The application features a Spring MVC backend and uses JSP views for the frontend.

## 2. Prerequisites

Before you begin, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK) 8 or higher
- Apache Maven for building the application
- A servlet container like Apache Tomcat
- MySQL or another compatible database

## 3. Project Structure

The project is structured as follows:

Save to grepper
banking-web-app/
├── src/ # Java source code
├── resources/ # Static resources (CSS, JS, etc.)
├── webapp/
| ├── views/ # JSP views
| 
└── pom.xml # Maven project configuration



## 4. Configuration

You can configure application properties, such as database connection, server port, and other settings, in the `application.properties` file located in the `src/main/resources` directory.

## 5. Database Setup

The application uses a MySQL database. You should create a MySQL database and update the database connection configuration in `application.properties` accordingly. update sql url.



## 6.Access the application at http://localhost:8080/home.jsp

## 7. Web Controller
The application includes a BankingWebController that handles various URL mappings for different pages:

/banking-web/home: Home page.
/banking-web/login: Login page.
/banking-web/signUp: Sign-up page.
/banking-web/dashboard: User dashboard.
It also provides mappings for displaying lists of bank details, account statements, and transactions:

/bank-details-list: List of bank details.
/account-statements-list: List of account statements.
/transactions-list: List of transactions.

## 8. Session Management
The application uses session attributes to store user details, bank details, and account statements during a user's session. These attributes are set and retrieved within the BankingWebController to provide a seamless user experience.

user-details: Stores user-related information during a user's session.
bank-details: Stores bank-related information during a user's session.
accountStatements: Stores account statement-related information during a user's session.
Please make sure the session management is correctly configured to maintain user and banking data across different parts of the application.

Enjoy using the Banking Web Application!
