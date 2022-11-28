package com.example.pages;

import com.example.utils.Driver;
import org.junit.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Books_student extends Books {

    public Books_student() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindAll(@FindBy(css = "tbody tr td a"))
    private List<WebElement> borrowBookBtns;

    @FindBy(css = "div[class='toast-message']")
    private WebElement bookBorrowedMsg;

    public void borrowBook() {
        for (WebElement eachBtn : borrowBookBtns) {
            if (!eachBtn.getAttribute("class").endsWith("disabled")) {
                eachBtn.click();
                Driver.sleep(1);
                break;
            }
        }
        Assert.assertTrue(bookBorrowedMsg.isDisplayed());
    }

}