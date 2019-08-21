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
		int length = 8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringDataOptions options = (StringDataOptions) StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getLength()).isEqualTo(length);
		assertThat(options.isFirstCapital()).isTrue();
		assertThat(options.isAllCapital()).isFalse();
	}
	
	@Test
	public void shouldThrowExceptionWhenLengthIsLessThan1() {
		int length = -8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLengthIsNotANumber() {
		String length = "one";
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("length"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenfirstCapitalIsNotBoolean() {
		int length = 8;
		String firstCapital = "This is true";
		boolean allCapital = false;
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("first_cap"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenallCapitalIsNotBoolean() {
		int length = 8;
		boolean firstCapital = true;
		int allCapital = 0;
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("all_cap"));
		StringDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
}
