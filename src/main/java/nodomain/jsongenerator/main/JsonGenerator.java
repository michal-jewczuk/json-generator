package nodomain.jsongenerator.main;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.data.DataType;

public class JsonGenerator {
	
	public static void generateJson(int count) {

		JSONArray arr = parseStructureFile();

		int length = arr.length();
		for (int i = 0; i < length; i++) {
			JSONObject current = arr.getJSONObject(i);
			DataType type =  DataType.valueOf(current.getString("type"));
			System.out.println(type.createJsonFragment(null));
		}
	}
	
	private static JSONArray parseStructureFile() {
		String json_string = "{'types': ["
				+ "{'type': 'JSON_STRING','options' : []},"
				+ "{'type': 'JSON_STRING','options' : []},"
				+ "{'type': 'JSON_NUMBER','options' : []},"
				+ "{'type': 'JSON_STRING','options' : []},"
				+ "{'type': 'JSON_NUMBER','options' : []}"
				+ "]}";
		
		JSONObject obj = new JSONObject(json_string);
		return obj.getJSONArray("types");
	}
}
