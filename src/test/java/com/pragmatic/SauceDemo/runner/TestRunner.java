package com.pragmatic.SauceDemo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/Features/product.feature",
                    "src/test/resources/Features/cart.feature",
                    "src/test/resources/Features/checkout.feature",
                    "src/test/resources/Features/login.feature",
                    "src/test/resources/Features/navigationAndLogout.feature"},
        glue = {"com.pragmatic.SauceDemo.steps"},
        //name = {"Login with invalid credentials"},
        //tags = "@smoke or @regression",
        plugin = {"pretty", //Prints Gherkin steps in the console
                "html:target/cucumber-reports.html", // HTML report
                "json:target/cucumber.json"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
