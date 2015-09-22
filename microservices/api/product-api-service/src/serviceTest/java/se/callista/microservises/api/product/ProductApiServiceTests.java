package se.callista.microservises.api.product;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Test
public class ProductApiServiceTests {

	private String token;

	@BeforeClass
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

	@Test
	public void TestProductRestGet(){
		given().
				auth().oauth2(token).
				//header("Authorization","Bearer " + token).
				when().get("https://192.168.99.100/api/product/1").
				then().body("weight", equalTo(123));
	}


}
