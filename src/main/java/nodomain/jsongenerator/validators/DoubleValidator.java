package nodomain.jsongenerator.validators;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.validators.common.ItemValidator;

public enum DoubleValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		double lowerBound;
		double upperBound;
		int precision;
		
		try {
			lowerBound = structure.getDouble("lower_bound");
			upperBound = structure.getDouble("upper_bound");
			precision = structure.getInt("precision");
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		}
		
		if (upperBound <= lowerBound) {
			throw new ValidationException(Errors.LOWER_BOUND_HIGHER);
		}
		
		if (precision < 1) {
			throw new ValidationException(Errors.INVALID_PRECISION);
		}
		
		return true;
	}

}
