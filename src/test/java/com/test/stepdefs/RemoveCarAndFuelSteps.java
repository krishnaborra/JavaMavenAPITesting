package com.test.stepdefs;

import com.test.utils.CommonFunctions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.io.IOException;

public class RemoveCarAndFuelSteps extends CommonFunctions{

    @And("^user configures stubs for nino (.+) for taxyear (.+) for (.+) with POST request")
    public void userConfiguresPOSTWiremockServer(String nino, Integer taxYear, String actionType) throws IOException{
        configurePOSTRequest(nino, taxYear, actionType);
    }

    @Then("^the server should handle post for nino (.+) for taxyear (.+) for (.+) and return success status$")
    public void theservershouldhandlepostandreturnasuccessstatus(String nino, Integer taxYear, String actionType) throws IOException{
        postRequest(nino, taxYear, actionType);
    }

}
