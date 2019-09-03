package nodomain.jsongenerator.main;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.io.ReadWriteUtil;

public class JsonGenerator {
	
	public static void generateOutputFile(int count, String outputName) {
		JSONArray arr = parseStructureFile();
		StringBuilder output = generateJson(count, arr);
	
		ReadWriteUtil.writeToFile(output, outputName);
	}
	
	private static JSONArray parseStructureFile() {
		String json_string = ReadWriteUtil.readStructure();
		
		JSONObject obj = new JSONObject(json_string);
		return obj.getJSONArray("types");
	}
	
	public static StringBuilder generateJson(int count, JSONArray arr) {
		StringBuilder output = new StringBuilder();
		
		attachHeader(output, count);
		attachBody(output, count, arr);
		attachFooter(output, count);
		
		return output;
	}
	
	private static void attachHeader(StringBuilder output, int count) {	
		if (count > 1) {
			output.append("{ \"data\": [\n");
		}
	}
	
	private static void attachFooter(StringBuilder output, int count) {
		if (count > 1) {
			output.append("\n]}");
		}
	}
	
	private static void attachBody(StringBuilder output, int count, JSONArray arr) {
		String prefix="";
		for (int i = 0; i < count; i++){
			output.append(prefix);
			prefix = ",\n";	
			attachSingleJsonObject(output, arr);			
		}	
	}	
	
	private static void attachSingleJsonObject(StringBuilder output, JSONArray arr) {
		output.append("{\n");

		int length = arr.length();
		String prefix="";
		for (int i = 0; i < length; i++) {
			output.append(prefix);
			JSONObject current = arr.getJSONObject(i);
			DataType type =  DataType.valueOf(current.getString("type"));
			output.append("\t");
			output.append(type.createJsonFragment(current.getString("name"), current.getJSONObject("options")));
			prefix = ",\n";
		}
		
		output.append("\n}");
	}

}
