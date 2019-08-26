package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.data.parsers.PatternDataOptionsParser;

public enum PatternFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		PatternDataOptions options = 
				PatternDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generatePatternFragment(options));
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generatePatternFragment(PatternDataOptions options) {
		StringBuilder fragment = new StringBuilder("no data options");
		return fragment;
	}

}
