package nodomain.jsongenerator.validators.common;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.ValidationException;

public interface ItemValidator {

	boolean validateItem(JSONObject structure) throws ValidationException;
	
	default String getOptionName(String message) {
		Map<String, String> names = new HashMap<>();
		names.put("lower_bound", "lower bound");
		names.put("upper_bound", "upper bound");
		names.put("only_true", "only TRUE");
		names.put("only_false", "only FALSE");
		names.put("output_pattern", "output pattern");
		names.put("all_cap", "all Capital");
		names.put("first_cap", "first Capital");
		names.put("min_length", "min length");
		names.put("max_length", "max length");
		
		String[] parts = message.split("\"");
		
		return names.getOrDefault(parts[1], parts[1]);
	}
	 
}
