package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class PatternValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldReturnTrueWhenAllIsValid() throws ValidationException {
		String pattern = "4-";
		String connector = "+";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern 
							+ ", \"connector\": " + connector 
							+  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = PatternValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternTooShort() throws ValidationException {
		String pattern = "one";
		String connector = "+";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern 
							+ ", \"connector\": " + connector 
							+  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_STRING_PATTERN);
		PatternValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternNotAString() throws ValidationException {
		int pattern = 1;
		String connector = "+";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern 
							+ ", \"connector\": " + connector 
							+  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		PatternValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenConnectorNotAString() throws ValidationException {
		String pattern = "2-8-3";
		boolean connector = false;
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern 
							+ ", \"connector\": " + connector 
							+  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		PatternValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenAllCapNotABoolean() throws ValidationException {
		String pattern = "2-8-3";
		String connector = "connector";
		int allCap = 0;
		String json_string = "{\"pattern\": " + pattern 
							+ ", \"connector\": " + connector 
							+  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		PatternValidator.INSTANCE.validateItem(json_object);
	}
	
	
}
