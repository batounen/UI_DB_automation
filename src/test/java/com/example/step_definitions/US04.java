package com.example.step_definitions;

import com.example.pages.Books;
import com.example.utils.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class US04 {

    Books books = new Books();
    String bookName;

    @When("I open book {string}")
    public void iOpenBook(String bookName) {
        this.bookName = bookName;
        books.searchBook(bookName);
    }

    @Then("book information must match the Database")
    public void bookInformationMustMatchTheDatabase() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select name, author, year from books where name like '" + bookName + "'");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        List<String> expectedNAY = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                expectedNAY.add(resultSet.getString(i));
            }
        }
        System.out.println("Visible books on UI = " + books.getVisibleBookNAY());
        System.out.println("expectedNAY = " + expectedNAY);
        Assertions.assertEquals(expectedNAY, books.getVisibleBookNAY());
    }

}