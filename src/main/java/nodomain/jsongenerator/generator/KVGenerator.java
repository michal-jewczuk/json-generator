package nodomain.jsongenerator.generator;

import java.util.Random;

import org.json.JSONObject;

public interface KVGenerator {
	
	static Random rnd = new Random();

	StringBuilder generateKeyValue(String name, JSONObject dataOptions);
	
	default StringBuilder generateKey(String name) {
		StringBuilder fragment = new StringBuilder();
		fragment.append("\"").append(name).append("\": ");
		
		return fragment;
	}
	
}
