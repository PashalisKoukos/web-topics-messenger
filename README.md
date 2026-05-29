# Topics Exchange Web Application

This web application provides a collaborative platform where registered users can exchange ideas, define topics of interest, and manage their discussions. The system allows users to interact with a centralized database to post messages and explore various community topics.

## About the Project
This project was developed as part of the "Web Technologies and Applications" course. It demonstrates the implementation of a full-stack web application using Java Servlets, relational database management, and asynchronous client-server communication.

### Key Functionality
* **User Authentication**: Secure sign-up, sign-in, and account management capabilities.
* **User Profile**: Registered users can update their account details, including password management.
* **Topic Management**: Users can define new topics of interest if they do not already exist in the system.
* **Messaging**: Users can post messages related to specific topics of interest.
* **Account Deletion**: Users have the functionality to manage and delete their personal accounts.

## Technical Stack
* **Back-end**: Java Servlets, JDBC, and Gson for JSON serialization/deserialization.
* **Front-end**: HTML5, CSS3, and JavaScript (Fetch API) for asynchronous, dynamic page updates.
* **Database Management**: 
    * **XAMPP**: Used to host the MariaDB/MySQL database server locally.
    * **MariaDB JDBC Driver**: The connector library that enables Java to communicate with the MariaDB database.

## Database Schema
The application relies on a relational database (`projectdb`) consisting of three main entities:
* **users**: Stores unique uname and SHA-256 hashed passwords (upasshash).
* **topics**: Contains id, unique name, and description.
* **messages**: Tracks id, topic_id, user_id, msg, and date_sent.
<img width="2043" height="373" alt="image" src="https://github.com/user-attachments/assets/9ef13976-b6f7-48fb-bf43-8b4f15746a79" />


## Setup Instructions
1. **Database Setup**: 
    * Open XAMPP Control Panel and start the MySQL/MariaDB service.
    * Import the `projectdb.sql` file via phpMyAdmin or your preferred database tool.
2. **Connector**: Ensure the `mariadb-java-client-x.x.x.jar` file is present in the `WEB-INF/lib` directory of your project.
3. **Configuration**: 
    * Open `DatabaseConnection.java` and update the connection URL (usually `jdbc:mariadb://localhost:3306/projectdb`) and credentials (default XAMPP username is `root` with no password).
4. **Deployment**: Deploy the project in a Java-compliant Web Container (e.g., Apache Tomcat 9 or higher).
5. **Environment**: Ensure the provided utility files (`Util.java` and `ServletUtil.java`) are correctly placed in the `org.hua.it2024041` package to support JSON processing and hashing.

## Project Structure
The project follows the standard Maven-based Java Web Application structure to ensure modularity and ease of maintenance:
* **src/main/java**: Contains the core Java source code (org.hua.it2024041), including Servlets, Database connectivity logic, hashing algorithms, and utility classes.
* **src/main/webapp**: The central directory for all front-end resources, including static HTML files, dynamic JSP pages for server-side generated content, and dedicated folders (css, js, imgs) for styling, client-side scripting, and media files.
* **WEB-INF**: Contains critical configuration files necessary for the deployment, routing, and security of the web application.
