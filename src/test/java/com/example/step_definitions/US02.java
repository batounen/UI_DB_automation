package com.example.step_definitions;

import com.example.pages.Dashboard;
import com.example.pages.Login;
import com.example.utils.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class US02 {

    Login login = new Login();
    Dashboard dashboard = new Dashboard();
    int numberOfBorrowedBooksUI;
    int numberOfBorrowedBooksDB;

    @Given("user login as a librarian")
    public void userLoginAsALibrarian() {
        login.positive_login();
    }

    @When("user take borrowed books number")
    public void userTakeBorrowedBooksNumber() {
        numberOfBorrowedBooksUI = Integer.parseInt(dashboard.getBorrowedBooks().getText());
    }

    @Then("borrowed books number information must match with DB")
    public void borrowedBooksNumberInformationMustMatchWithDB() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select count(*) as borrowedBooks from book_borrow where is_returned = 0");
        resultSet.next();
        numberOfBorrowedBooksDB = resultSet.getInt(1);
        System.out.println("numberOfBorrowedBooksDB = " + numberOfBorrowedBooksDB);
        System.out.println("numberOfBorrowedBooksUI = " + numberOfBorrowedBooksUI);
        Assert.assertEquals(numberOfBorrowedBooksDB, numberOfBorrowedBooksUI);
    }
}