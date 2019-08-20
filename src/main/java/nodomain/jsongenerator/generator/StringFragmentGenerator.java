package nodomain.jsongenerator.generator;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.util.StringUtil;

public enum StringFragmentGenerator implements FragmentGenerator {

	INSTANCE;

	public StringBuilder generateFragment(String name, DataOptions options) {
		StringDataOptions so = (StringDataOptions) options;
			
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generateStringFragment(so));
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generateStringFragment(StringDataOptions so) {
		
		StringBuilder fragment = new StringBuilder();
		String generated = StringUtil.generateStringFragment(so.getLength());
	
		if (so.isAllCapital()) {
			fragment = new StringBuilder(generated.toUpperCase());
		} else {
			if (so.isFirstCapital()) {
				fragment = StringUtil.capitalizeFirstLetter(generated);
			} else {
				fragment = new StringBuilder(generated);
			}
		}
		
		return fragment;
	}
	
	
}
