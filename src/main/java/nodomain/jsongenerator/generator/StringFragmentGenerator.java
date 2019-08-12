package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;

public enum StringFragmentGenerator implements FragmentGenerator {

	INSTANCE;

	public StringBuilder generateFragment(DataOptions options) {
		StringBuilder fragment = new StringBuilder();
		fragment.append("string");
		return fragment;
	}
	
	
}
