package nodomain.jsongenerator.validators;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.validators.common.ItemValidator;

public enum StringValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		int minLength;
		int maxLength;
		boolean firstCapital;
		boolean allCapital;
		
		try {
			minLength = structure.getInt("min_length");
			maxLength = structure.getInt("max_length");
			firstCapital = structure.getBoolean("first_cap");
			allCapital = structure.getBoolean("all_cap");
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		}
		
		if (minLength < 1) {
			throw new ValidationException(Errors.INVALID_LENGTH_SIZE);
		}
		
		if (maxLength < minLength) {
			throw new ValidationException(Errors.LOWER_BOUND_HIGHER);
		}
		
		if (firstCapital && allCapital) {
			throw new ValidationException(Errors.FIRST_AND_ALL);
		}
		
		return true;
	}

}
