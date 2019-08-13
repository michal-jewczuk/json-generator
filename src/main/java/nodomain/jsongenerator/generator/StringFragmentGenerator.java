package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;

public enum StringFragmentGenerator implements FragmentGenerator {

	INSTANCE;

	public StringBuilder generateFragment(String name, DataOptions options) {
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"string\"");
		
		return fragment;
	}
	
	
}
