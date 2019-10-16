package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class DoubleValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldReturnTrueWhenAllIsValid() throws ValidationException {
		double lowerBound = 0.0;
		double upperBound = 0.25;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = DoubleValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerIsNotHigherThanUpper() throws ValidationException {
		double lowerBound = 0.0;
		double upperBound = 0.0;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.LOWER_BOUND_HIGHER);
		DoubleValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPrecisionNotGreaterThatZero() throws ValidationException {
		double lowerBound = 0.0;
		double upperBound = 1.0;
		int precision = 0;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_PRECISION);
		DoubleValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerNotADouble() throws ValidationException {
		boolean lowerBound = false;
		double upperBound = 1.0;
		int precision = 3;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		DoubleValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperNotADouble() throws ValidationException {
		double lowerBound = 2.234;
		String upperBound = "30L";
		int precision = 3;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		DoubleValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPrecisionNotAnInteger() throws ValidationException {
		double lowerBound = 2.234;
		double upperBound = 100.0;
		String precision = "precision";
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound 
							+  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		DoubleValidator.INSTANCE.validateItem(json_object);
	}
	
}
