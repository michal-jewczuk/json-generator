package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

public class StringFragmentGeneratorTest {

	@Test
	public void shouldGenerateStringOfGivenLength() {
		int minLength = 12;
		int maxLength = 12;
		boolean firstCapital = false;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		
		assertThat(fragment.toString()).contains(name);
		assertThat(fragment.toString().length()).isEqualTo(minLength + name.length() + 6);
		assertThat(extractValue(fragment.toString()).length()).isEqualTo(minLength + 2);
	}
	
	@Test
	public void shouldGenerateStringOfGivenLengthWhenMinNotMax() {
		int minLength = 12;
		int maxLength = 22;
		boolean firstCapital = false;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		
		assertThat(fragment.toString()).contains(name);
		assertThat(fragment.toString().length()).isBetween(minLength + name.length() + 6, maxLength + name.length() + 6);
	}
	
	@Test
	public void shouldGenerateStringThatIsAllCaps() {
		int minLength = 7;
		int maxLength = 7;
		boolean firstCapital = false;
		boolean allCapital = true;
		String name = "jsonString";
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());

		assertThat(result).isEqualTo(result.toUpperCase());
	}
	
	@Test
	public void shouldGenerateStringThatHasCapitalizedFirstLetterOnly() {
		int minLength = 25;
		int maxLength = 25;
		boolean firstCapital = true;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());
		List<String> letters = new ArrayList<>();
		for (int i = 1, j = 2; i < minLength + 1; i++, j++) {
			letters.add(result.substring(i, j));
		}
		
		assertThat(letters.get(0)).isEqualTo(letters.get(0).toUpperCase());
		letters.remove(0);
		assertThat(letters).noneMatch(l -> l.equals(l.toUpperCase()));
	}
	
	@Test
	public void shouldGenerateStringOfonlyNonCapitalLetters() {
		int minLength = 13;
		int maxLength = 13;
		boolean firstCapital = false;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"min_length\": " + minLength + ", \"max_length\": " + maxLength 
				+ ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());
		List<String> letters = new ArrayList<>();
		for (int i = 1, j = 2; i < minLength + 1; i++, j++) {
			letters.add(result.substring(i, j));
		}

		assertThat(letters).noneMatch(l -> l.equals(l.toUpperCase()));
	}
	
	
	private String extractValue(String res) {
		String[] str = res.split(":");
		return str[1].trim();
	}
}
