# Phoenix Backend API Server

This project contains the implementation of open source Phoenix Finance App. 

The project is built on:
* Spring Boot
* Spring Data
* MySQL 8.0

You can open the project directly with Intellij Community. 

To run the project, you need your MySQL credentials declared on environment variables. You can either IntelliJ's run configuration or add these variable in your system. The variable names and values are following:
* PHOENIX_DB_HOST=<your_db_host>
* PHOENIX_DB_USR=<db_user_name>
* PHOENIX_DB_PWD=<db_password>

For example:
* PHOENIX_DB_HOST=jdbc:mysql://localhost:3306/phoenix_dev_db
* PHOENIX_DB_USR=root
* PHOENIX_DB_PWD=password