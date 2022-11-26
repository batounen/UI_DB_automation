package com.example.step_definitions;

import com.example.utils.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class TestBase {

    public String url = Driver.getProperty("dburl"), username = Driver.getProperty("dbusername"), password = Driver.getProperty("dbpassword");
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public DatabaseMetaData dbMetaData;
    public ResultSetMetaData resultSetMetaData;

    @BeforeEach
    public void setup() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection was successful!");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.out.println("ERROR HAS OCCURRED: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        try {
            if( resultSet!=null)  resultSet.close();
            if( statement!=null)  statement.close();
            if( connection!=null)  connection.close();
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE CLOSING RESOURCES " + e.getMessage());
        }
    }

}