import io.restassured.path.json.JsonPath;

public class reusableMethods {
	
	public static String rawToJson(String response,String string) {
		JsonPath js= new JsonPath(response);
		String actualString= js.getString(string); 
		return actualString;
	}

}
