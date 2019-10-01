package nodomain.jsongenerator.generator;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.main.JsonGenerator;

public enum ObjectKVGenerator implements KVGenerator {
	
	INSTANCE;
	
	private int count;
	private JSONArray structure;

	@Override
	public StringBuilder generateKeyValue(String name, JSONObject options) {
		count = options.getInt("count");
		structure = options.getJSONArray("structure");
		
		StringBuilder fragment = generateKey(name);
		fragment.append(generateValue());
		
		return fragment;
	}

	private StringBuilder generateValue() {
		return JsonGenerator.generateJson(count, structure, true);
	}

}
