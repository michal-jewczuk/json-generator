package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class ObjectValidatorTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldReturnTrueWhenAllIsValidAndStructureEmpty() throws ValidationException {
		int count = 3;
		String structure = "[]";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = ObjectValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldReturnTrueWhenAllIsValidAndStructureNotEmpty() throws ValidationException {
		int count = 3;
		String structure = "[{\"type\": \"JSON_BOOL\", \"options\": {\"only_true\": true, \"only_false\": false}}]";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = ObjectValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenCountIsLessThan1() throws ValidationException {
		int count = -1;
		String structure = "[]";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.INVALID_COUNT);
		ObjectValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenCountNotANumber() throws ValidationException {
		String count = "one";
		String structure = "[]";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		ObjectValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenStructureInvalidFormat() throws ValidationException {
		int count = 1;
		String structure = "{}";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		ObjectValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenNestedElementIsInvalid() throws ValidationException {
		int count = 1;
		String structure = "[{\"type\": \"JSON_BOOL\", \"name\": \"bool-test\", " 
				+ "\"options\": {\"only_true\": true, \"only_false\": true}}]";
		String json_string = "{\"count\": " + count 
							+ ", \"structure\": " + structure + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.TRUE_AND_FALSE);
		ObjectValidator.INSTANCE.validateItem(json_object);
	}
}
