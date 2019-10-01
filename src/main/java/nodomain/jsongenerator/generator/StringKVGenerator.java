package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.util.StringUtil;

public enum StringKVGenerator implements KVGenerator {

	INSTANCE;
	
	private int minLength;
	private int maxLength;
	private boolean firstCapital;
	private boolean allCapital;

	@Override
	public StringBuilder generateKeyValue(String name, JSONObject options) {
		minLength = options.getInt("min_length");
		maxLength = options.getInt("max_length");
		firstCapital = options.getBoolean("first_cap");
		allCapital = options.getBoolean("all_cap");
			
		StringBuilder fragment = generateKey(name);
		fragment.append("\"");
		fragment.append(generateValue());
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generateValue() {		
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
