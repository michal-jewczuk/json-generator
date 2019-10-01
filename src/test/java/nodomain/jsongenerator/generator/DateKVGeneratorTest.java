package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;
import org.json.JSONObject;
import org.junit.Test;

public class DateKVGeneratorTest {
	
	@Test
	public void shouldReturnCorrectDateWhenBoundsEquals() {
		String name = "date";
		String lowerBound = "2008-11-01";
		String upperBound = "2008-11-01";
		String outputPattern = "uuuu-MM-dd";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder generated = DateKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		String parsed = extractString(generated.toString());
		
		assertThat(generated.toString().length()).isEqualTo(name.length() + 6 + outputPattern.length());
		assertThat(parsed).isEqualTo(lowerBound);
	}
	
	@Test
	public void shouldReturnOnlyCorrectYear() {
		String name = "date";
		String lowerBound = "2012-01-01";
		String upperBound = "2012-12-31";
		String outputPattern = "uuuu";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder generated = DateKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		String parsed = extractString(generated.toString());
		
		assertThat(parsed).isEqualTo("2012");
	}
	
	@Test
	public void shouldReturnCorrectYearMonth() {
		String name = "date";
		String lowerBound = "2035-07-01";
		String upperBound = "2035-07-31";
		String outputPattern = "MM uuuu";
		String json_string = "{\"lower_bound\": " + lowerBound 
				+ ", \"upper_bound\": " + upperBound +  ", \"output_pattern\": " + outputPattern + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder generated = DateKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		String parsed = extractString(generated.toString());
		
		assertThat(parsed).isEqualTo("07 2035");		
	}
	
	
	private String extractString(String res) {
		String[] str = res.split(":");
		return str[1].replaceAll("\"", "").trim();
	}

}
