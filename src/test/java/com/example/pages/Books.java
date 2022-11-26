package com.example.pages;

import com.example.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Books {

    public Books() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#book_categories")
    private WebElement bookCategories;

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


}