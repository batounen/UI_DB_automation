package com.example.step_definitions;

import com.example.pages.Books;
import io.cucumber.java.en.*;

import java.sql.SQLException;

public class US06 {

    Books books = new Books();

    @When("the librarian click to add book")
    public void theLibrarianClickToAddBook() {
        books.addNewBookBtn();
    }

    @And("the librarian enter book name {string}")
    public void theLibrarianEnterBookName(String bookName) {
        books.enterBookName(bookName);
    }

    @When("the librarian enter ISBN {string}")
    public void theLibrarianEnterISBN(String isbn) {
        books.enterBookISBN(isbn);
    }

    @And("the librarian enter year {string}")
    public void theLibrarianEnterYear(String year) {
        books.enterBookYear(year);
    }

    @When("the librarian enter author {string}")
    public void theLibrarianEnterAuthor(String author) {
        books.enterBookAuthor(author);
    }

    @And("the librarian choose the book category {string}")
    public void theLibrarianChooseTheBookCategory(String category) {
        books.selectBookCategory(category);
    }

    @And("the librarian click to save changes")
    public void theLibrarianClickToSaveChanges() {
        books.saveNewBookEntry();
    }

    @Then("the librarian verify new book by {string}")
    public void theLibrarianVerifyNewBookBy(String bookName) {
        books.verifyNewBookEntryUI(bookName);
    }

    @Then("the librarian verify new book from database by {string}")
    public void theLibrarianVerifyNewBookFromDatabaseBy(String bookName) throws SQLException {
        books.verifyNewBookEntryDB(bookName);
    }

}