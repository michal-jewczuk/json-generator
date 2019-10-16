package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class NumberValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldReturnTrueWhenAllIsValid() throws ValidationException {
		long lowerBound = 500L;
		long upperBound = 500L;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = NumberValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerIsHigherThanUpper() throws ValidationException {
		long lowerBound = 501L;
		long upperBound = 500L;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.LOWER_BOUND_HIGHER);
		NumberValidator.INSTANCE.validateItem(json_object);		
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerNotANumber() throws ValidationException {
		boolean lowerBound = true;
		long upperBound = 500L;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		NumberValidator.INSTANCE.validateItem(json_object);		
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperNotANumber() throws ValidationException {
		long lowerBound = 501L;
		String upperBound = "not a numer";
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		NumberValidator.INSTANCE.validateItem(json_object);		
	}
	
}
