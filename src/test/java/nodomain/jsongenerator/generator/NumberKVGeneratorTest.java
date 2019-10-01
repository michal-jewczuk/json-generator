package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Test;

public class NumberKVGeneratorTest {
	
	@Test
	public void shouldCorectlyGenerateNumberGreaterThanZero() {
		long lowerBound = 500L;
		long upperBound = 500L;
		String name = "generated";
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = NumberKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		
		assertThat(result.toString()).contains(name);
		assertThat(extractLong(result.toString())).isEqualTo(lowerBound);
	}
	
	@Test
	public void shouldCorectlyGenerateNumberLessThanZero() {
		long lowerBound = -10L;
		long upperBound = -1L;
		String name = "generated";
		String json_string = "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = NumberKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		
		assertThat(extractLong(result.toString())).isBetween(lowerBound, upperBound);
	}
	
	private long extractLong(String res) {
		String[] str = res.split(":");
		return Long.parseLong(str[1].trim());
	}
}
