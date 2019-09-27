package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.util.StringUtil;

public enum StringFragmentGenerator implements FragmentGenerator {

	INSTANCE;
	
	private int minLength;
	private int maxLength;
	private boolean firstCapital;
	private boolean allCapital;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		minLength = options.getInt("min_length");
		maxLength = options.getInt("max_length");
		firstCapital = options.getBoolean("first_cap");
		allCapital = options.getBoolean("all_cap");
			
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generateStringFragment());
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generateStringFragment() {		
		StringBuilder fragment = new StringBuilder();
		String generated = StringUtil.generateStringFragment(generateLength(), AppConfig.STRING_SYMBOLS);
	
		if (allCapital) {
			fragment = new StringBuilder(generated.toUpperCase());
		} else {
			if (firstCapital) {
				fragment = StringUtil.capitalizeFirstLetter(generated);
			} else {
				fragment = new StringBuilder(generated);
			}
		}
		
		return fragment;
	}

	private int generateLength() {
		if (minLength == maxLength) {
			return minLength;
		}
		
		return rnd.ints(1, minLength, maxLength).sum();
	}
	
}
