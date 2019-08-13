package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	public StringBuilder generateFragment(String name, DataOptions options) {
		StringBuilder fragment = generateBegining(name);
		fragment.append(System.currentTimeMillis());

		return fragment;
	}

}
