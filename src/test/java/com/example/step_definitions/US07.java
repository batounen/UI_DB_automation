package com.example.step_definitions;

import com.example.pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

public class US07 {

    Login login = new Login();
    Dashboard dashboard = new Dashboard();
    Books books = new Books();
    Books_student books_student = new Books_student();
    Borrowing_Books_Student borrowing_books_student = new Borrowing_Books_Student();

    @Given("I login as a student")
    public void iLoginAsAStudent() {
        login.positive_login_student();
    }

    @And("I search book name called {string}")
    public void iSearchBookNameCalled(String bookName) {
        books.searchBook(bookName);
    }

    @When("I click Borrow Book")
    public void iClickBorrowBook() {
        books_student.borrowBook();
    }

    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String moduleName) {
        dashboard.moduleSelector(moduleName);
    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() throws SQLException {
        borrowing_books_student.verifyBookBorrow();
    }


}