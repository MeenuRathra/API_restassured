package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteOneProduct {

	String baseuri="https://techfios.com/api-prod/api/product";
	String id="6388";
	@Test(priority=1)
	public void deleteOneProduct() {  
				Response response = given().baseUri(baseuri).header("Content-Type", "application/json").auth().preemptive()
						.basic("demo@techfios.com", "abc123")
						.queryParam("id",id)
						.when()
						.delete("/delete.php")
						.then().extract().response();

				String actualResponseBody = response.getBody().asString();
				System.out.println("Actual Response Body:" + actualResponseBody);

				JsonPath jp = new JsonPath(actualResponseBody);
			System.out.println(response.getStatusCode());
	}

	@Test(priority=2)
	public void readOneUpdatedProduct() {  
				Response response = given().baseUri(baseuri)
						.header("Content-Type", "application/json")
						.auth().preemptive()
						.basic("demo@techfios.com", "abc123")
						.queryParam("id", id).
						 when()
						.get("/read_one.php")
						.then().extract().response();

				String actualResponseBody = response.getBody().asString();
				System.out.println("Actual Response Body:" + actualResponseBody);

				JsonPath jp = new JsonPath(actualResponseBody);
				System.out.println(response.getStatusCode());
	}}			
