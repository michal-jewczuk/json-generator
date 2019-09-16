package nodomain.jsongenerator.generator;

import java.util.Random;

import org.json.JSONObject;

public interface FragmentGenerator {
	
	static Random rnd = new Random();

	StringBuilder generateFragment(String name, JSONObject dataOptions);
	
	default StringBuilder generateBegining(String name) {
		StringBuilder fragment = new StringBuilder();
		fragment.append("\"");
		fragment.append(name);
		fragment.append("\": ");
		
		return fragment;
	}
	
}
