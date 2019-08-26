package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.junit.Test;

public class PatternFragmenGeneratorTest {
	
	@Test
	public void shouldCreateAllCapsStringWithNoConnector() {
		String name = "patern_test";
		String pattern = "4-";
		String connector = ">";
		boolean allCap = true;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = PatternFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String extracted = extractString(result.toString());
		
		assertThat(extracted).isEqualTo(extracted.toUpperCase());
		assertThat(result.length()).isEqualTo(name.length() + 6 + 4);
		assertThat(result.toString().contains(connector)).isFalse();
	}
	
	@Test
	public void shouldCreateStringWithThreeElements() {
		String name = "patern_test";
		String pattern = "1-2-3";
		String connector = "%";
		boolean allCap = false;
		String json_string = "{\"pattern\": " + pattern + ", \"connector\": " + connector +  ", \"all_cap\": " + allCap + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = PatternFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String extracted = extractString(result.toString());
		String[] splitted = extracted.toString().split(connector);	
		
		assertThat(extracted).isNotEqualTo(extracted.toUpperCase());
		assertThat(result.length()).isEqualTo(name.length() + 6 + 8);
		assertThat(result.toString().contains(connector)).isTrue();
		assertThat(splitted.length).isEqualTo(3);
		assertThat(splitted[0].length()).isEqualTo(1 + 1);
		assertThat(splitted[1].length()).isEqualTo(2);
		assertThat(splitted[2].length()).isEqualTo(3 + 1);
	}
	
	private String extractString(String res) {
		String[] str = res.split(":");
		return str[1].trim();
	}
}
