package nodomain.jsongenerator.validators;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.data.DataType;

public enum MainValidator {

	INSTANCE;
	
	public Map<String, String> validateStructure(String structure) {
		Map<String, String> errors = new HashMap<>();	
		
		JSONObject obj = new JSONObject(structure);
		JSONArray arr = obj.getJSONArray("types");
		
		int length = arr.length();
		for (int i = 0; i < length; i++) {

			JSONObject current = arr.getJSONObject(i);
			DataType type = DataType.valueOf(current.getString("type"));
			try {
				type.validate(current.getJSONObject("options"));
			} catch (Exception e) {
				errors.put(current.getString("name"), e.getLocalizedMessage());
			}
		}
		
		return errors;
	}
}
