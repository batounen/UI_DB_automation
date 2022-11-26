package com.example.pages;

import com.example.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Dashboard {

    public Dashboard() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#user_count")
    private WebElement userCount;

    @FindBy(css = "#book_count")
    private WebElement books;

    @FindBy(css = "#borrowed_books")
    private WebElement borrowedBooks;

    @FindBy(css = "#user_avatar")
    private WebElement userInfo;

    @FindAll(@FindBy(css = "#navbarCollapse li .title"))
    private List<WebElement> allModuleNames;

    @FindAll(@FindBy(css = "#navbarCollapse li[class='nav-item'] a"))
    private List<WebElement> allModules;

    public WebElement getUserInfo() {
        return userInfo;
    }

    public WebElement getBooks() {
        return books;
    }

    public WebElement getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getUserCount() {
        return Integer.parseInt(userCount.getText());
    }

    public void moduleSelector(String moduleName) {
        for (int i = 0; i < allModuleNames.size(); i++) {
            if (allModuleNames.get(i).getText().equals(moduleName)) {
                allModules.get(i).click();
                Driver.sleep(2);
            }
        }
    }

}