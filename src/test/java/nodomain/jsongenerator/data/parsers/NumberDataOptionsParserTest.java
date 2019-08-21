package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;


public class NumberDataOptionsParserTest {
	
	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldCorrectlyCreateObject() {
		long numMin = -10L;
		long numMax = 13L;
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";
		JSONObject json_object = new JSONObject(json_string);
		NumberDataOptions options = (NumberDataOptions) NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getNumberMin()).isEqualTo(numMin);
		assertThat(options.getNumberMax()).isEqualTo(numMax);
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenMinGraterThatMax() {
		long numMin = 10L;
		long numMax = 8L;
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
	
	@Test
	public void shouldThrowJSONExceptionWhenMinCouldNotBeParsedToLong() {
		String numMin = "ten";
		long numMax = 8L;
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";	
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("number_min"));
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
	
	@Test
	public void shouldThrowJSONExceptionWhenMaxCouldNotBeParsedToLong() {
		String numMax = "ten";
		long numMin = 8L;
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";	
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("number_max"));
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
}
