package nodomain.jsongenerator.validators;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.validators.common.ItemValidator;

public enum ObjectValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		int count;
		JSONArray children;
		
		try {
			count = structure.getInt("count");
			children = structure.getJSONArray("structure");
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		}
		
		if (count < 1) {
			throw new ValidationException(Errors.LOWER_BOUND_HIGHER);
		}
		
		validateChildren(children);
		
		return true;
	}

	private void validateChildren(JSONArray children) throws ValidationException {
		int length = children.length();
		for (int i = 0; i < length; i++) {
			JSONObject current = children.getJSONObject(i);
			DataType type = DataType.valueOf(current.getString("type"));
			try {
				type.validate(current.getJSONObject("options"));
			} catch (ValidationException ve) {
				StringBuilder sb = new StringBuilder()
						.append(current.getString("name"))
						.append(": ")
						.append(ve.getMessage());
				throw new ValidationException(sb.toString());
			}
		}
	}

}
