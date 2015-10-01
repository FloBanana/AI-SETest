package de.haw.hamburg.hawai.cdt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.net.ssl.HttpsURLConnection;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootApplication
public class ProductApiServiceCDT {

    private static final Logger LOG = LoggerFactory.getLogger(ProductApiServiceCDT.class);

    private String token;

    static {
        // for localhost testing only
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

    public void setupSecurityToken(){
        // Define relaxed SSL Auth, since we use self-signed certificates
        RestAssured.useRelaxedHTTPSValidation();
        // Register JSON Parser for plain text responses
        RestAssured.registerParser("text/plain", Parser.JSON);
        // Order and extract
        token =
                given().
                        param("grant_type","password").
                        param("client_id","acme").
                        param("username","user").
                        param("password","password").
                        when().post("https://acme:acmesecret@192.168.99.100:9999/uaa/oauth/token").then().
                        extract().path("access_token");

    }

    public void TestProductRestGet(){
        given().
                auth().oauth2(token).
                //header("Authorization","Bearer " + token).
                        when().get("https://192.168.99.100/api/product/1").
                then().body("weight", equalTo(123));
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApiServiceCDT.class, args);
    }
}
