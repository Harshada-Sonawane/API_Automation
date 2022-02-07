import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import RequestBody.AddPlaceBody;

public class Basics {

	public static void main(String[] args) {
		
		//given- all input details
		//when - submit the api -resouce,http methods
		//then- validate the response
		
		//validate if add place api is working as expected
		RestAssured.baseURI=("https://rahulshettyacademy.com");
		String addPlaceApiResponse= given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(AddPlaceBody.addPlaceRequestBody())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("****Response from add place api******= "+addPlaceApiResponse);
		
		//retrive place id from add place api response
		String placeId= reusableMethods.rawToJson(addPlaceApiResponse,"place_id");
		System.out.println("****place id from add place api***= "+placeId);
		
		//pass place id to update place api request
		String newAddress= "Capetown ,Africa";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n"
				+ "    \"place_id\": \"" + placeId +"\",\r\n"
				+ "    \"address\": \"" + newAddress +"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		//check if address is updated for given place id in get place api response
		String getPlaceApiResponse= given().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();

		System.out.println("****Response from get place api****= "+getPlaceApiResponse);
		String actualAddress= reusableMethods.rawToJson(getPlaceApiResponse,"address");
		
		
		Assert.assertEquals(actualAddress,newAddress);

	}

}
