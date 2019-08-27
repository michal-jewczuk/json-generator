package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public class BoolDataOptionsParserTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void sholdParseBothFalse() {
		boolean onlyTrue = false;
		boolean onlyFalse = false;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		BoolDataOptions options = BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.isOnlyTrue()).isFalse();
		assertThat(options.isOnlyFalse()).isFalse();
	}
	
	@Test
	public void sholdParseTrueFalse() {
		boolean onlyTrue = true;
		boolean onlyFalse = false;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		BoolDataOptions options = BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.isOnlyTrue()).isTrue();
		assertThat(options.isOnlyFalse()).isFalse();
	}
	
	@Test
	public void sholdParseFalseTrue() {
		boolean onlyTrue = false;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		BoolDataOptions options = BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.isOnlyTrue()).isFalse();
		assertThat(options.isOnlyFalse()).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenBothTrue() {
		boolean onlyTrue = true;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenOnlyTrueNotBoolean() {
		int onlyTrue = 1;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("only_true"));
		BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenOnlyFalseNotBoolean() {
		boolean onlyTrue = false;
		String onlyFalse = "test";
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("only_false"));
		BoolDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
}
