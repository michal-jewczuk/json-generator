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
		long lowerBound = -10L;
		long upperBound = 13L;
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		NumberDataOptions options = (NumberDataOptions) NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getLowerBound()).isEqualTo(lowerBound);
		assertThat(options.getUpperBound()).isEqualTo(upperBound);
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWhenMinGraterThatMax() {
		long lowerBound = 10L;
		long upperBound = 8L;
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
	
	@Test
	public void shouldThrowJSONExceptionWhenMinCouldNotBeParsedToLong() {
		String lowerBound = "ten";
		long upperBound = 8L;
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";	
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("lower_bound"));
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
	
	@Test
	public void shouldThrowJSONExceptionWhenMaxCouldNotBeParsedToLong() {
		long lowerBound = 8L;
		String upperBound = "ten";		
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";	
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("upper_bound"));
		NumberDataOptionsParser.INSTANCE.parseDataOptions(json_object);	
	}
}
