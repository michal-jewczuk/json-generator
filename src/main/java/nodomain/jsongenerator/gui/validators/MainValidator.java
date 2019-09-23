package nodomain.jsongenerator.gui.validators;

import java.util.HashMap;
import java.util.Map;

public enum MainValidator {

	INSTANCE;
	
	public Map<String, String> validateStructure(String structure) {
		Map<String, String> errors = new HashMap<>();
		errors.put("JSON_OBJECT", "count < 1");
		errors.put("JSON_BOOL", "allTrue && allFalse");
		errors.put("JSON_Number", "min > max");
		
		return errors;
	}
}
