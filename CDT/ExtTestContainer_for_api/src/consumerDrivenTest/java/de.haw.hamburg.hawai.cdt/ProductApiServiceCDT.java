package de.haw.hamburg.hawai.cdt;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * This class is an example of CDTs.
 */
@Test
public class ProductApiServiceCDT {

    private String token;

    /**
     * The URL under which the Service is accessable.
     * On pure-local deployments this is usually 192.168.99.100
     * when Running Docker on OS X or Windows.
     * Change to 127.0.0.1 when using Linux.
     * Change to the real IP Address if using a production like environment.
     */
    private String apiServiceBaseURL = "192.168.99.100";

    @BeforeClass
    public void setupSecurityToken(){
        // Define relaxed SSL Auth, since we use self-signed certificates
        RestAssured.useRelaxedHTTPSValidation();
        // Register JSON Parser for plain text responses
        RestAssured.registerParser("text/plain", Parser.JSON);
        // Order and extract auth-token
        token =
                given().
                        param("grant_type", "password").
                        param("client_id", "acme").
                        param("username", "user").
                        param("password", "password").
                        when().post("https://acme:acmesecret@" + apiServiceBaseURL + ":9999/uaa/oauth/token").then().
                        extract().path("access_token");

    }

    @Test
    public void TestProductRestGet(){
        given().
                // use auth token for oauth v2 header
                auth().oauth2(token).
                // make call to API
                when().get("https://" + apiServiceBaseURL + "/api/product/1").
                // check result
                then().body("weight", equalTo(123));
    }

}
