package nodomain.jsongenerator.main;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.io.ReadWriteUtil;

public class JsonGenerator {
	
	public static void generateJson(int count, String outputName) {

		StringBuilder output = generateHeader(count);

		String prefix="\n";
		for (int i = 0; i < count; i++){
			output.append(prefix);
			prefix = ",\n";
			output.append("{\n");
			createSingleJsonObject(output);
			output.append("\n}");
		}	

		attachFooter(output, count);
		
		ReadWriteUtil.writeToFile(output, outputName);
	}
	
	private static void createSingleJsonObject(StringBuilder output) {
		JSONArray arr = parseStructureFile();

		int length = arr.length();
		for (int i = 0; i < length; i++) {
			JSONObject current = arr.getJSONObject(i);
			DataType type =  DataType.valueOf(current.getString("type"));
			output.append("\t").append(type.createJsonFragment(current.getString("name"), current.getJSONObject("options")));
			
			if (i < length - 1) {
				output.append(",\n");
			}
		}
	}

	private static JSONArray parseStructureFile() {
		
		String json_string = ReadWriteUtil.readStructure();
		
		JSONObject obj = new JSONObject(json_string);
		return obj.getJSONArray("types");
	}
	
	private static StringBuilder generateHeader(int count) {
		StringBuilder output = new StringBuilder();
		
		if (count > 1) {
			output.append("{\"data\": [");
		}
		
		return output;
	}
	
	private static void attachFooter(StringBuilder output, int count) {
		if (count > 1) {
			output.append("\n]}");
		}
	}
}
