package testcases;


import  static io.restassured.RestAssured.*;

import org.hamcrest.Matcher;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadAllproducts {

	@Test
public void readAllproducts() {
	Response response=
	given()
	.baseUri("https://techfios.com/api-prod/api/product")
	.header("Content-Type","application/json; charset=UTF-8")
	.auth().preemptive().basic("demo@techfios.com","abc123").
	
	when()
	.get("/read.php").
	
	then()
	.extract().response();
long responsetime=	response.getTime();
if(responsetime<=2500) {
	System.out.println("timewith in range");}
	else {System.out.println("out of range");
}
String responseHeader= response.getHeader("Content-Type");
System.out.println(responseHeader);
Assert.assertEquals(responseHeader,"application/json; charset=UTF-8");

String responsebody=response.getBody().asString();
JsonPath jp= new JsonPath(responsebody);
String firstproductid=jp.getString("records[0].id");
System.out.println("first product is:"+firstproductid);

int statuscode=response.getStatusCode();
	System.out.println(statuscode);
	
}

	
	}
