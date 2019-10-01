package nodomain.jsongenerator.validators;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.validators.common.ItemValidator;

public enum NumberValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		long lowerBound;
		long upperBound;
		
		try {
			lowerBound = structure.getLong("lower_bound");
			upperBound = structure.getLong("upper_bound");
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		}
		
		if (upperBound < lowerBound) {
			throw new ValidationException(Errors.LOWER_BOUND_HIGHER);
		}
		
		return true;
	}

}
