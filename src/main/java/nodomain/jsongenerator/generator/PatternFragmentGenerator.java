package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.PatternDataOptions;

public enum PatternFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, DataOptions options) {
		PatternDataOptions op = (PatternDataOptions) options;
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generatePatternFragment(op));
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generatePatternFragment(PatternDataOptions op) {
		StringBuilder fragment = new StringBuilder("no data options");
		return fragment;
	}

}
