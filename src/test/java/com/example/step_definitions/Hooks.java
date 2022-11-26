package com.example.step_definitions;

import com.example.utils.DB_Util;
import com.example.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setup() throws Exception {
        Driver.createDriver();
        DB_Util.createConnection(Driver.getProperty("DBurl"), Driver.getProperty("DBUsername"), Driver.getProperty("DBPassword"));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenShots = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShots, "image/png", scenario.getName());
        }
        Driver.cleanUpDriver();
    }

}