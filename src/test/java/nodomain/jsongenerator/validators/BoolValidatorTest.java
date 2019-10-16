package nodomain.jsongenerator.validators;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.exceptions.Errors;
import nodomain.jsongenerator.exceptions.ValidationException;

public class BoolValidatorTest {
	
	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldNotThrowAnyErrorsWhenBothFalse() throws ValidationException {
		boolean onlyTrue = false;
		boolean onlyFalse = false;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = BoolValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldNotThrowAnyErrorsWhenOnlyOneTrueIsTrue() throws ValidationException {
		boolean onlyTrue = true;
		boolean onlyFalse = false;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = BoolValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldNotThrowAnyErrorsWhenOnlyOneFalseIsTrue() throws ValidationException {
		boolean onlyTrue = false;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		boolean result = BoolValidator.INSTANCE.validateItem(json_object);
		
		assertThat(result).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenBothTrue() throws ValidationException {
		boolean onlyTrue = true;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.TRUE_AND_FALSE);
		BoolValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenOnlyTrueNotBoolean() throws ValidationException {
		String onlyTrue = "This is true";
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		BoolValidator.INSTANCE.validateItem(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenOnlyFalseNotBoolean() throws ValidationException {
		boolean onlyTrue = false;
		long onlyFalse = 0L;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(ValidationException.class);
		excE.expectMessage(Errors.PARSING_ERROR);
		BoolValidator.INSTANCE.validateItem(json_object);
	}
}
