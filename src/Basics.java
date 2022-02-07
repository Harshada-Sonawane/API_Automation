import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import RequestBody.AddPlaceBody;

public class Basics {

	public static void main(String[] args) {
		
		//given- all input details
		//when - submit the api -resouce,http methods
		//then- validate the response
		
		//validate if add place api is working as expected
		RestAssured.baseURI=("https://rahulshettyacademy.com");
		String response= given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(AddPlaceBody.addPlaceRequestBody())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//retrive place id from add place api response
		JsonPath js= new JsonPath(response);
		String placeId= js.getString("place_id");
		System.out.println("place id from add place api= "+placeId);
		
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
		String response1=given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
		.when().put("maps/api/place/get/json")
		.then().assertThat().statusCode(200).body("address",equalTo(newAddress)).extract().response().asString();
		
		System.out.println(response1);

	}

}
