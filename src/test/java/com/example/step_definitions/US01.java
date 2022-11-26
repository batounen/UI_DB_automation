package com.example.step_definitions;

import com.example.pages.Dashboard;
import com.example.pages.Login;
import com.example.utils.DB_Util;
import com.example.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class US01 {

    Login login = new Login();
    Dashboard dashboard = new Dashboard();
    Set<String> ids = new LinkedHashSet<>();
    List<String> actualColumnNames = new ArrayList<>();
    int numberOfIDs;

    @When("Execute query to get all IDs from users")
    public void executeQueryToGetAllIDsFromUsers() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select id from users");
        numberOfIDs = DB_Util.getRowCount();
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
    }

    @Then("verify all users has unique ID")
    public void verifyAllUsersHasUniqueID() {
        login.positive_login();
        int userCountUI = dashboard.getUserCount();
        System.out.println("Number Of IDs from DB = " + numberOfIDs);
        System.out.println("Number Of IDs from DB in HashSet = " + ids.size());
        System.out.println("Number Of users from UI = " + userCountUI);
        Assertions.assertEquals(numberOfIDs, ids.size());
        DB_Util.destroy();
    }

    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select * from users");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            actualColumnNames.add(resultSetMetaData.getColumnName(i));
        }
    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedColumnNames) {
        System.out.println("expectedColumnNames = " + expectedColumnNames);
        System.out.println("columnNames = " + actualColumnNames);
        Assertions.assertEquals(expectedColumnNames, actualColumnNames);
    }
}