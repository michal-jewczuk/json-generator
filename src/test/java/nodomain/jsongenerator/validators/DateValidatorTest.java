package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class DateValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldReturnTrueWhenAllIsCorrect() throws ValidationException {
		String lowerBound = "2008-11-01";
		String upperBound = "2008-11-10";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = DateValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenOneIsNotADailyDate() throws ValidationException {
		String lowerBound = "2012-11-14";
		String upperBound = "2013-01";
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.NOT_A_DATE);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerIsNotADate() throws ValidationException {
		String lowerBound = "not a date";
		String upperBound = "2013-01-02";
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.NOT_A_DATE);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperIsNotADate() throws ValidationException {
		String lowerBound = "2018-01-02";
		String upperBound = "2012-02-30";
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.NOT_A_DATE);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperIsBeforeLower() throws ValidationException {
		String lowerBound = "2018-01-02";
		String upperBound = "2012-02-11";
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.LOWER_BOUND_HIGHER);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperIsNotAString() throws ValidationException {
		String lowerBound = "2018-01-02";
		int upperBound = 2012;
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerIsNotAString() throws ValidationException {
		boolean lowerBound = false;
		String upperBound = "2012-02-11";
		String outputPattern = "uuuu-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternIsNotValid() throws ValidationException {
		String lowerBound = "2008-01-02";
		String upperBound = "2012-02-11";
		String outputPattern = "not a pattern";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_DATE_PATTERN);
		DateValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternContainsTimeSymbols() throws ValidationException {
		String lowerBound = "2018-01-02";
		String upperBound = "2022-02-11";
		String outputPattern = "MM-dd mm+HH";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_DATE_PATTERN);
		DateValidator.INSTANCE.validateItem(json_object);
	}
}
