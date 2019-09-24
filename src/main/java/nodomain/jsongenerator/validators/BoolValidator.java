package nodomain.jsongenerator.validators;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public enum BoolValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		boolean onlyTrue;
		boolean onlyFalse;
		
		try {
			onlyTrue = structure.getBoolean("only_true");
			onlyFalse = structure.getBoolean("only_false");
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		}
		
		if (onlyTrue && onlyFalse) {
			throw new ValidationException(Errors.TRUE_AND_FALSE);
		}
		
		return true;
	}

}
