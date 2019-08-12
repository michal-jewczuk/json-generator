package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	public StringBuilder generateFragment(DataOptions options) {
		StringBuilder fragment = new StringBuilder();
		fragment.append("number");
		return fragment;
	}

}
