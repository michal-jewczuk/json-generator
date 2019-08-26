package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public class PatternDataOptionsParserTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldCorrectlyCreateObjectWithTwoElements() {
		String pattern = "4-2";
		String connector = ">";
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		PatternDataOptions options = PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getParts()).containsExactly(4, 2);
		assertThat(options.getConnector()).isEqualTo(connector);
		assertThat(allCap).isFalse();
	}
	
	@Test
	public void shouldCorrectlyCreateObjectWithOneElement() {
		String pattern = "5-";
		String connector = ">";
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		PatternDataOptions options = PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getParts()).containsExactly(5);
		assertThat(options.getConnector()).isEqualTo(connector);
		assertThat(allCap).isFalse();
	}
	
	@Test
	public void shouldCorrectlyCreateObjectWithFourElements() {
		String pattern = "1-2-3-4";
		String connector = ".";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		PatternDataOptions options = PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getParts()).containsExactly(1, 2, 3, 4);
		assertThat(options.getConnector()).isEqualTo(connector);
		assertThat(allCap).isTrue();
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternContainsOnlyOneNumber() {
		String pattern = "1";
		String connector = "_";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("pattern"));
		PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternContainsIllegalCharacters() {
		String pattern = "10-one-3";
		String connector = "_";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("one"));
		PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternHasInvalidFormat() {
		String pattern = "10.5.3";
		String connector = "_";
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenConnectorNotAString() {
		String pattern = "5-3";
		boolean connector = false;
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("connector"));
		PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenAllCapNotABoolean() {
		String pattern = "10-";
		String connector = "_";
		String allCap = "test";
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("all_cap"));
		PatternDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
}
