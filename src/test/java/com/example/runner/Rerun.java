package com.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports.html",
                "junit:target/junit/junit-report.xml",
                "rerun:target/rerun.txt",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"},
        glue = "com/example/step_definitions",
        features = "@target/rerun.txt"
)
public class Rerun {
}