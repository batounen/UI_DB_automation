package com.example.pages;

import com.example.utils.DB_Util;
import com.example.utils.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Books {

    public Books() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#book_categories")
    private WebElement bookCategories;

    @FindBy(css = "input[type='search']")
    private WebElement bookSearchBox;

    @FindAll(@FindBy(css = "thead th"))
    private List<WebElement> tableHeader;

    @FindAll(@FindBy(css = "tbody tr"))
    private List<WebElement> bookList;

    @FindAll(@FindBy(css = "tbody tr td"))
    private List<WebElement> bookListDetails;

    @FindBy(css = ".add_book_btn")
    private WebElement addBookBtn;

    @FindBy(css = "input[type='text'][name='name']")
    private WebElement newBookName;

    @FindBy(css = "input[type='text'][name='isbn']")
    private WebElement newBookISBN;

    @FindBy(css = "input[type='text'][name='year']")
    private WebElement newBookYear;

    @FindBy(css = "input[type='text'][name='author']")
    private WebElement newBookAuthor;

    @FindBy(css = "#book_group_id")
    private WebElement newBookCategory;

    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;

    @FindBy(css = "button[type='cancel']")
    private WebElement closeBtn;

    @FindBy(css = "select[name='tbl_books_length']")
    private WebElement numberOfRecordsDD;

    public WebElement getNumberOfRecordsDD() {
        return numberOfRecordsDD;
    }

    public List<String> getBookCategories() {
        List<String> allBookCategoriesUI = new ArrayList<>();
        Select select = new Select(bookCategories);
        for (WebElement each : select.getOptions()) {
            if (!each.getText().equals("ALL")) {
                allBookCategoriesUI.add(each.getText());
            }
        }
        return allBookCategoriesUI;
    }

    public void searchBook(String bookToSearch) {
        bookSearchBox.sendKeys(bookToSearch);
        Driver.sleep(2);
    }

    public List<Map<String, String>> getVisibleBooks() {
        List<Map<String, String>> allBooks = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            Map<String, String> eachBookInfo = new LinkedHashMap<>();
            for (int k = 1; k < bookListDetails.size(); k++) {
                eachBookInfo.put(tableHeader.get(k).getText(), bookListDetails.get(k).getText());
            }
            allBooks.add(eachBookInfo);
        }
        return allBooks;
    }

    public List<String> getVisibleBookNAY() {
        List<String> nameAuthorYear = new ArrayList<>();
        String nay = "NameAuthorYear";
        for (int i = 0; i < bookList.size(); i++) {
            for (int k = 0; k < bookListDetails.size(); k++) {
                if (nay.contains(tableHeader.get(k).getText())) {
                    nameAuthorYear.add(bookListDetails.get(k).getText());
                }
            }
        }
        return nameAuthorYear;
    }

    public List<String> getVisibleBookNames() {
        List<String> bookNames = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            bookNames.add(bookListDetails.get(2).getText());
        }
        return bookNames;
    }

    public void addNewBookBtn() {
        addBookBtn.click();
        Driver.waitUntilClickable(submitBtn);
    }

    public void enterBookName(String bookName) {
        newBookName.sendKeys(bookName);
    }

    public void enterBookISBN(String isbn) {
        newBookISBN.sendKeys(isbn);
    }

    public void enterBookYear(String year) {
        newBookYear.sendKeys(year);
    }

    public void enterBookAuthor(String author) {
        newBookAuthor.sendKeys(author);
    }

    public void selectBookCategory(String category) {
        Select select = new Select(newBookCategory);
        select.selectByVisibleText(category);
    }

    public void saveNewBookEntry() {
        submitBtn.click();
//        Driver.waitUntilClickable(addBookBtn);
        Driver.sleep(2);
    }

    public void verifyNewBookEntryUI(String bookName) {
        searchBook(bookName);
        String bookNameFromUI = getVisibleBookNames().get(0);
        System.out.println("Newly entered book name = " + bookName);
        System.out.println("Newly entered book name search result from UI = " + bookNameFromUI);
        Assertions.assertTrue(bookNameFromUI.startsWith(bookName));
    }

    public void verifyNewBookEntryDB(String bookName) throws SQLException {
        ResultSet resultSet = DB_Util.runQuery("select name from books where name like '" + bookName + "' order by id desc");
        resultSet.next();
        String bookNameFromDB = resultSet.getString(1);
        System.out.println("Newly entered book name = " + bookName);
        System.out.println("Newly entered book name search result from DB = " + bookNameFromDB);
        Assertions.assertTrue(bookNameFromDB.startsWith(bookName));
    }

}