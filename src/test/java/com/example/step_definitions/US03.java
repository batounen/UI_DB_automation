package com.example.step_definitions;

import com.example.pages.Books;
import com.example.pages.Dashboard;
import com.example.utils.DB_Util;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class US03 {

    Dashboard dashboard = new Dashboard();
    Books books = new Books();

    List<String> expectedBookCategories = new ArrayList<>();
    List<String> actualBookCategories;

    @When("I navigate to {string} page")
    public void iNavigateToPage(String moduleName) {
        dashboard.moduleSelector(moduleName);
    }

    @And("I take all book categories in UI")
    public void iTakeAllBookCategoriesInUI() {
        actualBookCategories = books.getBookCategories();
    }

    @And("I execute query to get book categories")
    public void iExecuteQueryToGetBookCategories() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select name from book_categories");
        while (resultSet.next()) {
            expectedBookCategories.add(resultSet.getString(1));
        }
    }

    @Then("verify book categories must match book_categories table from db")
    public void verifyBookCategoriesMustMatchBook_categoriesTableFromDb() {
        System.out.println("expectedBookCategories = " + expectedBookCategories);
        System.out.println("actualBookCategories = " + actualBookCategories);
        Assertions.assertEquals(expectedBookCategories, actualBookCategories);
    }
}