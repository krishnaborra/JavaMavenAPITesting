package com.test.utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.hamcrest.CoreMatchers.anything;


import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommonFunctions {

    private static final String APPLICATION_JSON = "application/json";
    private static final String HOST = "localhost";
    private static final Integer WIREMOCK_PORT_NO = 8080;

    private final WireMockServer wireMockServer = new WireMockServer();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void startMockServer(){
        wireMockServer.start();
    }

    public void stopMockServer(){
        wireMockServer.stop();
    }

    public void configurePOSTRequest(String nino, Integer taxYear, String actionType){
        configureFor(HOST, WIREMOCK_PORT_NO);
        stubFor(post(urlEqualTo(FormUrl(nino, taxYear, actionType)))
                .withHeader("content-type", equalTo(APPLICATION_JSON))
                .withRequestBody(containing("version"))
                .willReturn(aResponse().withStatus(200)));
    }

    public void configureGETRequest(String nino, Integer taxYear, String actionType){
        configureFor(HOST, WIREMOCK_PORT_NO);
        stubFor(get(urlEqualTo(FormUrl(nino, taxYear, actionType)))
                .withHeader("accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(JsonString(actionType))));
    }

    public void postRequest(String nino, Integer taxYear, String actionType) throws IOException{
        HttpPost request = new HttpPost("http://" + HOST + ":" + WIREMOCK_PORT_NO + FormUrl(nino, taxYear, actionType));
        StringEntity entity = new StringEntity(JsonString(actionType));
        request.addHeader("content-type", APPLICATION_JSON);
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
        verify(postRequestedFor(urlEqualTo(FormUrl(nino, taxYear, actionType)))
                .withHeader("content-type", equalTo(APPLICATION_JSON)));
    }

    public void getRequest(String nino, Integer taxYear, String actionType) throws IOException, JSONException{

        HttpGet request = new HttpGet("http://" + HOST + ":" + WIREMOCK_PORT_NO + FormUrl(nino, taxYear, actionType));
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = convertResponseToString(httpResponse);

        System.out.println("ACTUAL" + responseString);

        verify(getRequestedFor(urlEqualTo(FormUrl(nino, taxYear, actionType))).withHeader("accept", equalTo(APPLICATION_JSON)));


    }

    private String FormUrl(String nino, Integer taxYear, String urlType){

        String returnUrl;

        switch (urlType){
            case "removeCarFuel":
                Integer empId = 1;
                Integer carId = 1;
                returnUrl = "/paye/" + nino + "/benefits/" + taxYear + "/" + empId + "/car/" + carId + "/remove";
                break;
            case "getCarBenefits":
                returnUrl = "/paye/" + nino + "/car-benefits/" + taxYear;
                break;
            default: throw new RuntimeException("Url Type not found");
        }
        return returnUrl;
    }

    private String JsonString(String actionType){
        InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("jsonData/" + actionType + ".json");
        String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

        return jsonString;
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }
}
