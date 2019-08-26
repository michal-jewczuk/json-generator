package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Test;

public class NumberFragmentGeneratorTest {
	
	@Test
	public void shouldCorectlyGenerateNumberGreaterThanZero() {
		long numMin = 500L;
		long numMax = 500L;
		String name = "generated";
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = NumberFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		
		assertThat(result.toString()).contains(name);
		assertThat(extractLong(result.toString())).isEqualTo(numMin);
	}
	
	@Test
	public void shouldCorectlyGenerateNumberLessThanZero() {
		long numMin = -10L;
		long numMax = -1L;
		String name = "generated";
		String json_string = "{\"number_min\": " + numMin + ", \"number_max\": " + numMax + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = NumberFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		
		assertThat(extractLong(result.toString())).isBetween(numMin, numMax);
	}
	
	private long extractLong(String res) {
		String[] str = res.split(":");
		return Long.parseLong(str[1].trim());
	}
}
