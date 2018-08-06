package com.test.stepdefs;

import com.test.utils.CommonFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.JSONException;

import java.io.IOException;

public class GetCarBenefitsSteps extends CommonFunctions {

    @And("^user configures stubs for nino (.+) for taxyear (.+) for (.+) with GET request")
    public void userConfiguresGETWiremockServer(String nino, Integer taxYear, String actionType) throws IOException {
        configureGETRequest(nino, taxYear, actionType);
    }

    @Then("^the server should handle get for nino (.+) for taxyear (.+) for (.+) and return success status$")
    public void theservershouldhandlegetandreturnasuccessstatus(String nino, Integer taxYear, String actionType) throws IOException, JSONException{
        getRequest(nino, taxYear, actionType);
    }

}
