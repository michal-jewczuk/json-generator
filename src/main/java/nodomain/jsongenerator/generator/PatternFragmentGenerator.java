package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.util.StringUtil;

public enum PatternFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	public static final String SPLITERATOR = "-";
	
	private int[] parts;
	private String connector;
	private boolean allCapital;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		String pattern = options.getString("pattern");
		connector = options.getString("connector");
		allCapital = options.getBoolean("all_cap");
		parts = parseStringPattern(pattern);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generatePatternFragment());
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generatePatternFragment() {	
		StringBuilder fragment = new StringBuilder();
		
		String connect = "";
		for (int part : parts) {
			fragment.append(connect);
			fragment.append(StringUtil.generateStringFragment(part, AppConfig.STRING_SYMBOLS));
			connect = connector;
		}
		
		if (allCapital) {
			fragment = new StringBuilder(fragment.toString().toUpperCase());
		}
		
		return fragment;
	}

	private int[] parseStringPattern(String pattern) {
		String[] patternParts = pattern.split(SPLITERATOR);
		int length = patternParts.length;
		int[] parts = new int[length];

		for (int i = 0; i < length; i++) {
			parts[i] = Integer.parseInt(patternParts[i]);
		}
		
		return parts;
	}
}
