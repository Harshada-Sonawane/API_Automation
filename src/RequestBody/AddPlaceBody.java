package RequestBody;

public class AddPlaceBody {
	
	public static String addPlaceRequestBody () {
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"sadhana misal\",\r\n"
				+ "  \"phone_number\": \"7798328256\",\r\n"
				+ "  \"address\": \"nashik maharashtra\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		
	}
	
	public static String updatePlaceRequestBody () {
		return "{\r\n"
				+ "    \"place_id\": \"7ce5e5c46f21d1624cd4ea783614b9be\",\r\n"
				+ "    \"address\": \"70 Summer walk, USA\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}";
		
	}

}
