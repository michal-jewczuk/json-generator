package nodomain.jsongenerator.validators;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.generator.PatternKVGenerator;

public enum PatternValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		String pattern;
		
		try {
			pattern = structure.getString("pattern");
			structure.getString("connector");
			structure.getBoolean("all_cap");
			parseStringPattern(pattern);
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		} catch (NumberFormatException e){
			throw new ValidationException(Errors.INVALID_STRING_PATTERN);
		}
		
		if (pattern.length() < 1) {
			throw new ValidationException(Errors.INVALID_PATTERN_LENGHT);
		}
		
		return true;
	}
	
	private void parseStringPattern(String pattern) {
		String[] patternParts = pattern.split(PatternKVGenerator.SPLITERATOR);
		
		for (String part: patternParts) {
			Integer.parseInt(part);
		}
	}

}
