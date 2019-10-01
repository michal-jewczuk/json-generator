package nodomain.jsongenerator.validators;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;
import nodomain.jsongenerator.validators.common.ItemValidator;

public enum DateValidator implements ItemValidator {
	
	INSTANCE;

	@Override
	public boolean validateItem(JSONObject structure) throws ValidationException {
		String lowerBound;
		String upperBound;
		String outputPattern;
		LocalDate lowerBoundLD;
		LocalDate upperBoundLD;
		
		try {
			lowerBound = structure.getString("lower_bound");
			upperBound = structure.getString("upper_bound");
			outputPattern = structure.getString("output_pattern");
			DateTimeFormatter.ofPattern(outputPattern);
			lowerBoundLD = LocalDate.parse(lowerBound);
			upperBoundLD = LocalDate.parse(upperBound);
		} catch (JSONException e) {
			throw new ValidationException(Errors.PARSING_ERROR + getOptionName(e.getMessage()));
		} catch (IllegalArgumentException e) {
			throw new ValidationException(Errors.INVALID_DATE_PATTERN);
		} catch (DateTimeException e) {
			throw new ValidationException(Errors.NOT_A_DATE + getOptionName(e.getMessage()));
		}	

		if (lowerBoundLD.isAfter(upperBoundLD)) {
			throw new ValidationException(Errors.LOWER_BOUND_HIGHER);
		}
		
		return true;
	}
	

}
