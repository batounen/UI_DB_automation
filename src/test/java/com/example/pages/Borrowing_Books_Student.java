package com.example.pages;

import com.example.utils.DB_Util;
import com.example.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Borrowing_Books_Student {

    public Borrowing_Books_Student() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindAll(@FindBy(css = "thead th"))
    private List<WebElement> tableHeaders;

    @FindAll(@FindBy(css = "tbody tr"))
    private List<WebElement> borrowedBooksList;

    @FindAll(@FindBy(xpath = "//tbody//tr//td[2]"))
    private List<WebElement> borrowedBookNames;

    @FindAll(@FindBy(xpath = "//tbody//tr//td[6]"))
    private List<WebElement> borrowedBooksReturnStatus;

    @FindAll(@FindBy(css = "tbody tr td"))
    private List<WebElement> borrowedBooksDetails;

    @FindAll(@FindBy(css = "tbody tr td a"))
    private List<WebElement> returnBorrowedBookBtns;

    public void verifyBookBorrow() throws SQLException {
        List<String> listOfBorrowedBooksNotReturnedUI = new ArrayList<>();
        for (int i = 0; i < borrowedBooksReturnStatus.size(); i++) {
            if (borrowedBooksReturnStatus.get(i).getText().contains("NOT RETURNED")) {
                listOfBorrowedBooksNotReturnedUI.add(borrowedBookNames.get(i).getText());
            }
        }
        ResultSet resultSet = DB_Util.runQuery("select b.name, u.email, bb.is_returned " +
                "from book_borrow bb " +
                "inner join users u on bb.user_id = u.id " +
                "inner join books b on bb.book_id = b.id " +
                "where u.email like 'student9@library' and bb.is_returned = 0 " +
                "order by bb.is_returned");
        List<String> listOfBorrowedBooksNotReturnedDB = new ArrayList<>();
        while (resultSet.next()) {
            listOfBorrowedBooksNotReturnedDB.add(resultSet.getString("name"));
        }
        System.out.println("List of Borrowed Books Not Returned DB = " + listOfBorrowedBooksNotReturnedDB);
        System.out.println("List of Borrowed Books Not Returned UI = " + listOfBorrowedBooksNotReturnedUI);
        Assertions.assertEquals(listOfBorrowedBooksNotReturnedDB, listOfBorrowedBooksNotReturnedUI);
        for (WebElement each : returnBorrowedBookBtns) {
            if (!each.getAttribute("class").contains("disabled")) {
                each.click();
            }
        }
    }
}