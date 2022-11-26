package com.example.step_definitions;

import com.example.utils.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class US05 {

    String actualPopularGenre;

    @When("I execute query to find most popular book genre")
    public void iExecuteQueryToFindMostPopularBookGenre() throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select bc.name, count(*) " +
                "from book_borrow bb " +
                "inner join books b on bb.book_id = b.id " +
                "inner join book_categories bc on b.book_category_id = bc.id " +
                "group by bc.name " +
                "order by 2 desc");
        resultSet.next();
        actualPopularGenre = resultSet.getString(1);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verifyIsTheMostPopularBookGenre(String categoryName) {
        System.out.println("Expected Popular Genre from requirement = " + categoryName);
        System.out.println("Actual Popular Genre from DB = " + actualPopularGenre);
        Assertions.assertEquals(categoryName, actualPopularGenre);
    }

}