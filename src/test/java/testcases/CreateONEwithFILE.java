package testcases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateONEwithFILE {

	String baseuri="https://techfios.com/api-prod/api/product";
	String file="src\\main\\java\\data\\payload.json";
    String firstProductId;
    String readoneProduct;

@Test
public void CreateProduct() {
Response response=
given().baseUri(baseuri)
.header("Content-Type","application/json; charset=UTF-8")
.auth().preemptive().basic("demo@techfios.com","abc123")
.body(new File(file)).

when()
.post("/create.php").
then()
.extract()
.response();
int responseCode=response.getStatusCode();
Assert.assertEquals(responseCode, 201);

long responseTime= response.getTimeIn(TimeUnit.MILLISECONDS);
System.out.println("Response time:"+responseTime);
String responseHeader= response.getHeader("Content-Type");
System.out.println(responseHeader);

String responsebody= response.getBody().asString();
JsonPath js= new JsonPath(responsebody);
System.out.println(responsebody);
String productmessage=js.getString("message");
System.out.println(productmessage);
Assert.assertEquals(productmessage, "Product was created.");

JsonPath js1= new JsonPath(new File(file));


System.out.println(js1.get("message"));
}

@Test(priority=2)
public void readallProduct() {
	
	Response response=
			given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-Type","application/json; charset=UTF-8")
			.auth().preemptive().basic("demo@techfios.com","abc123").
			
			when()
			.get("/read.php").
			
			then()
			.extract().response();

			  String responseBody = response.getBody().asString();
			  System.out.println("Response Body:" + responseBody);

			  JsonPath jp = new JsonPath(responseBody);
			  firstProductId = jp.getString("records[0].id");  
			  System.out.println("First Product Id:" + firstProductId);
			  readoneProduct = firstProductId;
			 }
	


@Test(priority=3)
public void readONEproduct() {


Response response=
given()
.baseUri(baseuri)
.auth().preemptive().basic("demo@techfios.com", "abc123")
.queryParam("id", readoneProduct)
.header("Content-Type","application/json; charset=UTF-8").

when()
.get("/read_one.php").

then()
.extract().response();
	String actualResponseBody = response.getBody().asString();
	System.out.println("Actual Response Body:" + actualResponseBody);

	JsonPath jp = new JsonPath(actualResponseBody);

	
 }  
	
	
	
}
