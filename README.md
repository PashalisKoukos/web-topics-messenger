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
* **Database**: MySQL/MariaDB based on the provided relational schema.

## Database Schema
The application relies on a relational database (`projectdb`) consisting of three main entities:
* **users**: Stores unique uname and SHA-256 hashed passwords (upasshash).
* **topics**: Contains id, unique name, and description.
* **messages**: Tracks id, topic_id, user_id, msg, and date_sent.

## Setup Instructions
1. **Database**: Import the projectdb.sql file into your local MariaDB/MySQL instance.
2. **Configuration**: Update your database connection settings in the DatabaseConnection.java file to match your local environment.
3. **Deployment**: Deploy the project in a Java-compliant Web Container (e.g., Apache Tomcat 9 or higher).
4. **Environment**: Ensure the provided utility files (Util.java and ServletUtil.java) are correctly placed in the org.hua.it2024041 package to support JSON processing and hashing.

## Project Deliverables
* **Source Code**: Contains all HTML, CSS, JavaScript, JSP, and Java source files.
* **Report**: A detailed PDF report documenting application functionality with screenshots.
* **Video Presentation**: A minimum 2-minute demonstration of the application's source code and functional capabilities.
