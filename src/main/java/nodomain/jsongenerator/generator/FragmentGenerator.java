package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;

public interface FragmentGenerator {

	StringBuilder generateFragment(String name, DataOptions options);
	
	default StringBuilder generateBegining(String name) {
		StringBuilder fragment = new StringBuilder();
		fragment.append("\"");
		fragment.append(name);
		fragment.append("\": ");
		
		return fragment;
	}
	
}
