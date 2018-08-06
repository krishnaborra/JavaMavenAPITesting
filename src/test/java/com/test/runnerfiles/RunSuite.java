package com.test.runnerfiles;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.test.stepdefs",
        tags = "@wip"
)
public class RunSuite {
}
