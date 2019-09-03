package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import java.time.LocalDate;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public class DateDataOptionsParserTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	
	@Test
	public void shouldParseToCorrectOutput() {
		String lowerBound = "2019-11-01";
		String upperBound = "2020-03-12";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		DateDataOptions options = DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getLowerBound()).isEqualTo(LocalDate.parse(lowerBound));
		assertThat(options.getUpperBound()).isEqualTo(LocalDate.parse(upperBound));
		assertThat(options.getOutputPattern()).isEqualTo(outputPattern);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerCannotBeParsedToString() {
		int lowerBound = 2019;
		String upperBound = "2020-12-31";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("lower_bound"));
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperCannotBeParsedToString() {
		String lowerBound = "2019-13-01";
		boolean upperBound = false;
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("upper_bound"));
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternCannotBeParsedToString() {
		String lowerBound = "2019-13-01";
		String upperBound = "2020-12-31";
		double outputPattern = 1.34;
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("output_pattern"));
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerIllegalFormat() {
		String lowerBound = "01-01-2019";
		String upperBound = "2020-12-31";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		excE.expectMessage("Bound is not a date!");
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperNotAValidDate() {
		String lowerBound = "2019-11-01";
		String upperBound = "2020-13-31";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		excE.expectMessage("Bound is not a date!");
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerGreaterThatUpper() {
		String lowerBound = "2021-11-01";
		String upperBound = "2020-01-31";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		excE.expectMessage("Lower bound > upper!");
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPatternInvalidFormat() {
		String lowerBound = "2001-11-01";
		String upperBound = "2020-01-31";
		String outputPattern = "uuuu-MM-dd g";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		excE.expectMessage("Illegal output pattern!");
		DateDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	
}
