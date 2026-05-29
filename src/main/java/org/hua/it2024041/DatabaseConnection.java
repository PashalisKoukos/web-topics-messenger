// author: Paschalis Koukos
// this class is responsible for establishing a connection to the database.
// It uses the MariaDB JDBC driver to connect to a MariaDB database named "projectdb" running on localhost
// with the default port 3306. The username is "root" and the password is an empty string. The getConnection method returns a Connection object that can be used to interact with the database.

package org.hua.it2024041;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseConnection {
    private DatabaseConnection() { // private constructor to prevent instantiation
    }

    /* Returns a connection to the MariaDB database */
    public static Connection getConnection() throws Exception { 
        Class.forName("org.mariadb.jdbc.Driver"); // load the MariaDB JDBC driver
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/projectdb", "root", "");// establish a connection to the database and return it
    }
}