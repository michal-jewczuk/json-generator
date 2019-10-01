package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

public class DoubleFragmentGeneratorTest {

	@Test
	public void shouldGenerateCorrectDouble() {
		String name = "my_double";
		double lowerBound = 0.0;
		double upperBound = 0.25;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = DoubleKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		String extracted = extractString(result.toString());
		String[] parts = extracted.split("\\.");
		
		assertThat(result.length()).isEqualTo(name.length() + 4 + precision + 2); //+2 is for 0.
		assertThat(parts[1].length()).isEqualTo(precision);
		assertThat(Double.parseDouble(extracted)).isBetween(lowerBound, upperBound);
	}
	
	@Test
	public void shouldGenerateElementsWithinBounds() {
		String name = "my_double";
		double lowerBound = 0.0;
		double upperBound = 0.25;
		int precision = 4;
		String json_string = "{\"lower_bound\": " + lowerBound 
							+ ", \"upper_bound\": " + upperBound +  ", \"precision\": " + precision + "}";
		JSONObject json_object = new JSONObject(json_string);
		List<Double> doubles = new ArrayList<>();
		
		for (int i = 0; i < 100000; i++) {
			StringBuilder result = DoubleKVGenerator.INSTANCE.generateKeyValue(name, json_object);
			String extracted = extractString(result.toString());
			doubles.add(Double.parseDouble(extracted));
		}
		
		assertThat(doubles).allMatch(d -> (d >= lowerBound && d < upperBound));
	}
	
	
	private String extractString(String res) {
		String[] str = res.split(":");
		return str[1].trim();
	}
}
