package nodomain.jsongenerator.generator;

import org.json.JSONArray;
import org.json.JSONObject;

import nodomain.jsongenerator.main.JsonGenerator;

public enum ObjectFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	private int count;
	private JSONArray structure;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		count = options.getInt("count");
		structure = options.getJSONArray("structure");
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateObjectFragment());
		
		return fragment;
	}

	private StringBuilder generateObjectFragment() {
		return JsonGenerator.generateJson(count, structure, true);
	}

}
