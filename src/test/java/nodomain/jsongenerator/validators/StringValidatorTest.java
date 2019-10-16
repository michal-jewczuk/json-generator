package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class StringValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();	
	
	@Test
	public void shouldReturnTrueWhenAllIsValid() throws ValidationException {
		int minLength = 12;
		int maxLength = 22;
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = StringValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenMinLengthIsLessThanZero() throws ValidationException {
		int minLength = -3;
		int maxLength = 22;
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_LENGTH_SIZE);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMaxLengthIsLessThanZero() throws ValidationException {
		int minLength = 2;
		int maxLength = -1;
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.LOWER_BOUND_HIGHER);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMaxLengthIsLessThanMin() throws ValidationException {
		int minLength = 2;
		int maxLength = 1;
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.LOWER_BOUND_HIGHER);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenBothTrue() throws ValidationException {
		int minLength = 2;
		int maxLength = 10;
		boolean firstCapital = true;
		boolean allCapital = true;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.FIRST_AND_ALL);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMinNotInt() throws ValidationException {
		boolean minLength = false;
		int maxLength = 10;
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMaxNotInt() throws ValidationException {
		int minLength = 2;
		String maxLength = "three";
		boolean firstCapital = false;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenFirstCapitalNotABoolean() throws ValidationException {
		int minLength = 2;
		int maxLength = 10;
		int firstCapital = 0;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenAllCapitalNotABoolean() throws ValidationException {
		int minLength = 2;
		int maxLength = 10;
		boolean firstCapital = false;
		String allCapital = "prawda";
		String json_string = "{\"min_length\": " + minLength 
							+ ", \"max_length\": " + maxLength 
							+ ", \"first_cap\": " + firstCapital 
							+ ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		StringValidator.INSTANCE.validateItem(json_object);
	}
	
}
