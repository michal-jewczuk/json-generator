package nodomain.jsongenerator.main;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.io.ReadWriteUtil;

public class JsonGenerator {
	
	public static String generateOutputFile(String structure, int count, String outputName) {
		JSONArray arr = parseStructureFile(structure);
		StringBuilder output = generateJson(count, arr, false);
	
		return ReadWriteUtil.writeToUniqueFile(output, outputName);
	}
	
	public static JSONArray parseStructureFile(String structure) {
		JSONObject obj = new JSONObject(structure);
		return obj.getJSONArray("types");
	}
	
	public static StringBuilder generateJson(int count, JSONArray arr, boolean nested) {
		StringBuilder output = new StringBuilder();
		
		attachHeader(output, count, nested);
		attachBody(output, count, arr);
		attachFooter(output, count, nested);
		
		return output;
	}
	
	private static void attachHeader(StringBuilder output, int count, boolean nested) {	
		if (count > 1) {
			if (!nested) {
				output.append("{\"")
						.append(AppConfig.DEFAULT_ARRAY_NAME)
						.append("\": [");
			} else {
				output.append("[");
			}
		}
	}
	
	private static void attachFooter(StringBuilder output, int count, boolean nested) {
		if (count > 1) {
			if (!nested) {
				output.append("]}");
			} else {
				output.append("]");
			}	
		}
	}
	
	private static void attachBody(StringBuilder output, int count, JSONArray arr) {
		String prefix="";
		for (int i = 0; i < count; i++){
			output.append(prefix);
			prefix = ",";	
			attachSingleJsonObject(output, arr);			
		}	
	}	
	
	private static void attachSingleJsonObject(StringBuilder output, JSONArray arr) {
		output.append("{");
		
		int length = arr.length();
		String prefix="";
		for (int i = 0; i < length; i++) {
			output.append(prefix);
			JSONObject current = arr.getJSONObject(i);
			DataType type = DataType.valueOf(current.getString("type"));
			output.append(type.createJsonFragment(current.getString("name"), 
												current.getJSONObject("options")));
			prefix = ",";
		}

		output.append("}");
	}

}
