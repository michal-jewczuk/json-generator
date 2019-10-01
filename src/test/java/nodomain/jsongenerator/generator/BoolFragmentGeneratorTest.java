package nodomain.jsongenerator.generator;

import static org.assertj.core.api.Assertions.assertThat;
import org.json.JSONObject;
import org.junit.Test;

public class BoolFragmentGeneratorTest {

	@Test
	public void shouldGenerateOnlyTrue() {
		String name = "test_bool";
		boolean onlyTrue = true;
		boolean onlyFalse = false;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = BoolKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		boolean parsed = extractBool(result.toString());
		
		assertThat(parsed).isTrue();
	}
	
	@Test
	public void shouldGenerateOnlyFalse() {
		String name = "test_bool";
		boolean onlyTrue = false;
		boolean onlyFalse = true;
		String json_string = "{\"only_true\": " + onlyTrue + ", \"only_false\": " + onlyFalse + "}";
		JSONObject json_object = new JSONObject(json_string);
		
		StringBuilder result = BoolKVGenerator.INSTANCE.generateKeyValue(name, json_object);
		boolean parsed = extractBool(result.toString());
		
		assertThat(parsed).isFalse();
	}

	private boolean extractBool(String res) {
		String[] str = res.split(":");
		return Boolean.parseBoolean(str[1].trim());
	}
	
}
