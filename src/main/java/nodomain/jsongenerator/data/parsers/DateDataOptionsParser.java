package nodomain.jsongenerator.data.parsers;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum DateDataOptionsParser implements DataOptionsParser {
	
	INSTANCE;

	@Override
	public DateDataOptions parseDataOptions(JSONObject options) {
		String lowerBound;
		String upperBound;
		String outputPattern;
		
		try {
			lowerBound = options.getString("lower_bound");
			upperBound = options.getString("upper_bound");
			outputPattern = options.getString("output_pattern");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		LocalDate lowerBoundLD = convertStringToLocalDate(lowerBound);
		LocalDate upperBoundLD = convertStringToLocalDate(upperBound);
		
		if (lowerBoundLD.isAfter(upperBoundLD)) {
			throw new IllegalArgumentException("Lower bound > upper!");
		}
		validateFormatPattern(outputPattern);
		
		return new DateDataOptions(lowerBoundLD, upperBoundLD, outputPattern);
	}

	private void validateFormatPattern(String format) {
		try {
			DateTimeFormatter.ofPattern(format);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Illegal output pattern!");
		}
	}

	private LocalDate convertStringToLocalDate(String dateString) {	
		LocalDate result;
		try {
			result = LocalDate.parse(dateString);
		} catch (DateTimeException e) {
			throw new IllegalArgumentException("Bound is not a date!");
		}
		
		return result;
	}

}
