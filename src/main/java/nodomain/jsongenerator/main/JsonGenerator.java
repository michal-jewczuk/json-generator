package nodomain.jsongenerator.main;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.io.ReadWriteUtil;

public class JsonGenerator {
	
	public static String generateOutputFile(String structure, int count, String outputName) {
		JSONArray arr = parseStructureFile(structure);
		StringBuilder output = generateJson(count, arr, AppConfig.NESTED_GROUND);
	
		return ReadWriteUtil.writeToUniqueFile(output, outputName);
	}
	
	public static JSONArray parseStructureFile(String structure) {
		JSONObject obj = new JSONObject(structure);
		return obj.getJSONArray("types");
	}
	
	public static StringBuilder generateJson(int count, JSONArray arr, int nestedLevel) {
		StringBuilder output = new StringBuilder();
		
		attachHeader(output, count, nestedLevel);
		attachBody(output, count, arr, nestedLevel);
		attachFooter(output, count, nestedLevel);
		
		return output;
	}
	
	private static void attachHeader(StringBuilder output, int count, int nestedLevel) {	
		if (count > 1) {
			if (nestedLevel == AppConfig.NESTED_GROUND) {
				output.append("{ \"data\": [");
			} else {
				output.append("[");
			}
		}
		output.append("\n");
	}
	
	private static void attachFooter(StringBuilder output, int count, int nestedLevel) {
		if (count > 1) {
			output.append("\n");
			attachTabs(output, nestedLevel - 1);
			output.append("]");	
			if (nestedLevel == AppConfig.NESTED_GROUND) {
				output.append("}");
			}
		}
	}
	
	private static void attachBody(StringBuilder output, int count, JSONArray arr, int nestedLevel) {
		String prefix="";
		for (int i = 0; i < count; i++){
			output.append(prefix);
			prefix = ",\n";	
			attachSingleJsonObject(output, arr, nestedLevel);			
		}	
	}	
	
	private static void attachSingleJsonObject(StringBuilder output, JSONArray arr, int nestedLevel) {
		appendTop(output, nestedLevel);

		int length = arr.length();
		String prefix="";
		for (int i = 0; i < length; i++) {
			output.append(prefix);
			JSONObject current = arr.getJSONObject(i);
			DataType type = DataType.valueOf(current.getString("type"));
			attachTabs(output, nestedLevel);
			output.append(type.createJsonFragment(current.getString("name"), current.getJSONObject("options")));
			prefix = ",\n";
		}

		appendBottom(output, nestedLevel);
	}
	
	private static void appendTop(StringBuilder output, int nestedLevel) {
		attachTabs(output, nestedLevel);
		output.append("{\n");		
	}
	
	private static void appendBottom(StringBuilder output, int nestedLevel) {
		output.append("\n");
		attachTabs(output, nestedLevel);
		output.append("}");		
	}

	private static void attachTabs(StringBuilder output, int nestedLevel) {
		for (int i = 0; i < nestedLevel; i++) {
			output.append("\t");
		}
	}

}
