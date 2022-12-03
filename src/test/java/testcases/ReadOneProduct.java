package testcases;
import  static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class ReadOneProduct {
	String baseURI="https://techfios.com/api-prod/api/product";
	SoftAssert softassert= new SoftAssert();
	
	@Test
public void readONEproduct() {
	

Response response=
given()
.baseUri(baseURI)
.auth().preemptive().basic("demo@techfios.com", "abc123")
.queryParam("id", "6212")
.header("Content-Type","application/json; charset=UTF-8").

when()
.get("/read_one.php").

then()
.extract().response();

int responsecode=response.getStatusCode();
String header=response.getHeader("Content-Type");
String responsebody=response.getBody().asString();
JsonPath jp= new JsonPath(responsebody);
if (responsebody!=null){
	System.out.println(responsebody);}
else {System.out.println("null");
}
System.out.println(header);
System.out.println(responsecode);

String responsename=jp.get("name");
String productprice= jp.getString("price");
System.out.println("responsename is:"+responsename);
System.out.println("productprice is:"+productprice);








}
}