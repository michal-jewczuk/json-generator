package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public class StringDataOptionsParserTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldCorrectlyParseValidData() {
		int minLength = 8;
		int maxLength = 8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringDataOptions options = (StringDataOptions) StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getMinLength()).isEqualTo(minLength);
		assertThat(options.getMaxLength()).isEqualTo(maxLength);
		assertThat(options.isFirstCapital()).isTrue();
		assertThat(options.isAllCapital()).isFalse();
	}
	
	@Test
	public void shouldThrowExceptionWhenLengthIsLessThan1() {
		int minLength = 8;
		int maxLength = 8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMaxIsLessThanMin() {
		int minLength = 2;
		int maxLength = 1;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMinLengthIsNotANumber() {
		String minLength = "one";
		int maxLength = 8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("min_length"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenMaxLengthIsNotANumber() {
		int minLength = 8;
		boolean maxLength = true;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("max_length"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenfirstCapitalIsNotBoolean() {
		int minLength = 8;
		int maxLength = 18;
		String firstCapital = "This is true";
		boolean allCapital = false;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("first_cap"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenallCapitalIsNotBoolean() {
		int minLength = 8;
		int maxLength = 10;
		boolean firstCapital = true;
		int allCapital = 0;
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("all_cap"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
}
