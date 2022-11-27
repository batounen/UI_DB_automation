package com.example.pages;

import com.example.utils.Driver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

    public Login() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    Dashboard dashboard;

    @FindBy(css = "img[class='mb-4']")
    private WebElement logo;

    @FindBy(css = "#inputEmail")
    private WebElement username;

    @FindBy(css = "#inputPassword")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement signInBtn;

    public void positive_login() {
        Driver.getDriver().get(Driver.getProperty("url"));
        if (Driver.getDriver().getTitle().equals("Login - Library")) {
            username.sendKeys(Driver.getProperty("validUsername"));
            password.sendKeys(Driver.getProperty("validPassword"));
            signInBtn.click();
            Driver.sleep(3);
        }
    }

    public void positive_login_student() {
        Driver.getDriver().get(Driver.getProperty("url"));
        if (Driver.getDriver().getTitle().equals("Login - Library")) {
            username.sendKeys(Driver.getProperty("validUsernameStudent"));
            password.sendKeys(Driver.getProperty("validPasswordStudent"));
            signInBtn.click();
            Driver.sleep(3);
        }
    }

}