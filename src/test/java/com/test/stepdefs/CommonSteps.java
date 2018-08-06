package com.test.stepdefs;

import com.test.utils.CommonFunctions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CommonSteps extends CommonFunctions{

    @Given("^user starts wiremock server$")
    public void userStartsWiremockServer() {
        startMockServer();
    }

    @When("^user stops wiremock server$")
    public void userStopsWiremockServer() {
        stopMockServer();
    }

}
