package com.example.step_definitions;

import com.example.utils.DB_Util;
import com.example.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before("@ui")
    public void setupUI() throws Exception {
        Driver.createDriver();
    }

    @After("@ui")
    public void tearDownUI(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenShots = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShots, "image/png", scenario.getName());
        }
        Driver.cleanUpDriver();
    }

    @Before("@db")
    public void setupDB() throws Exception {
        String url = "";
        if (System.getProperty("DBURL") != null) {
            url = System.getProperty("DBURL");
        } else {
            url = Driver.getProperty("DBurl");
        }
        DB_Util.createConnection(url, Driver.getProperty("DBUsername"), Driver.getProperty("DBPassword"));
    }

    @After("@db")
    public void tearDownDB() {
        DB_Util.destroy();
    }
}