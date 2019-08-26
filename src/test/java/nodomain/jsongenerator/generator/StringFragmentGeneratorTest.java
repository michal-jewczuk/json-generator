package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

public class StringFragmentGeneratorTest {

	@Test
	public void shouldGenerateStringOfGivenLength() {
		int length = 12;
		boolean firstCapital = false;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		
		assertThat(fragment.toString()).contains(name);
		assertThat(fragment.toString().length()).isEqualTo(length + name.length() + 6);
		assertThat(extractValue(fragment.toString()).length()).isEqualTo(length + 2);
	}
	
	@Test
	public void shouldGenerateStringThatIsAllCaps() {
		int length = 7;
		boolean firstCapital = false;
		boolean allCapital = true;
		String name = "jsonString";
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());

		assertThat(result).isEqualTo(result.toUpperCase());
	}
	
	@Test
	public void shouldGenerateStringThatHasCapitalizedFirstLetterOnly() {
		int length = 25;
		boolean firstCapital = true;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());
		List<String> letters = new ArrayList<>();
		for (int i = 1, j = 2; i < length + 1; i++, j++) {
			letters.add(result.substring(i, j));
		}
		
		assertThat(letters.get(0)).isEqualTo(letters.get(0).toUpperCase());
		letters.remove(0);
		assertThat(letters).noneMatch(l -> l.equals(l.toUpperCase()));
	}
	
	@Test
	public void shouldGenerateStringOfonlyNonCapitalLetters() {
		int length = 13;
		boolean firstCapital = false;
		boolean allCapital = false;
		String name = "jsonString";
		String json_string = "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder fragment = StringFragmentGenerator.INSTANCE.generateFragment(name, json_object);
		String result = extractValue(fragment.toString());
		List<String> letters = new ArrayList<>();
		for (int i = 1, j = 2; i < length + 1; i++, j++) {
			letters.add(result.substring(i, j));
		}

		assertThat(letters).noneMatch(l -> l.equals(l.toUpperCase()));
	}
	
	
	private String extractValue(String res) {
		String[] str = res.split(":");
		return str[1].trim();
	}
}
