package nodomain.jsongenerator.data.parsers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public class DoubleDataOptionsParserTest {

	@Rule
    public ExpectedException excE = ExpectedException.none();
	
	@Test
	public void shouldParseCorrectData() {
		double lowerBound = 1.0;
		double upperBound = 1.25;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		DoubleDataOptions options = DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getLowerBound()).isEqualTo(lowerBound);
		assertThat(options.getUpperBound()).isEqualTo(upperBound);
		assertThat(options.getPrecision()).isEqualTo(precision);
	}
	
	@Test
	public void shouldParseCorrectDataWhenBoundsAreInts() {
		int lowerBound = -1;
		int upperBound = 3;
		float precision = 2.0F;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		DoubleDataOptions options = DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
		
		assertThat(options.getLowerBound()).isEqualTo(lowerBound);
		assertThat(options.getUpperBound()).isEqualTo(upperBound);
		assertThat(options.getPrecision()).isEqualTo(2);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerHigherThanUpper() {
		double lowerBound = 2.0;
		double upperBound = 1.25;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPrecisionLessThan1() {
		double lowerBound = 2.0;
		double upperBound = 4.25;
		int precision = 0;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(IllegalArgumentException.class);
		DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenLowerNotDouble() {
		String lowerBound = "2_34";
		double upperBound = 4.25;
		int precision = 10;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("lower_bound"));
		DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenUpperNotDouble() {
		double lowerBound = 2.0;
		boolean upperBound = false;
		int precision = 2;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("upper_bound"));
		DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
	
	@Test
	public void shouldThrowExceptionWhenPrecisionNotInt() {
		double lowerBound = 2.0;
		double upperBound = 4.25;
		String precision = "2-0";
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		excE.expect(JsonParsingException.class);
		excE.expectMessage(containsString("precision"));
		DoubleDataOptionsParser.INSTANCE.parseDataOptions(json_object);
	}
}
