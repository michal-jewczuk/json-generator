package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum PatternDataOptionsParser implements DataOptionsParser {
	
	INSTANCE;

	@Override
	public PatternDataOptions parseDataOptions(JSONObject options) {	
		String pattern;
		String connector;
		boolean allCapital;
		
		if (options == null) {
			return new PatternDataOptions.Builder()
					.parts("-")
					.connector("")
					.allCapital(false)
					.build();
		}
		
		try {
			pattern = options.getString("pattern");
			connector = options.getString("connector");
			allCapital = options.getBoolean("all_cap");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (pattern.length() < 1) {
			throw new IllegalArgumentException("Length of pattern < 1!");
		}
		
		if (connector.length() < 1) {
			connector = AppConfig.DEFAULT_CONNECTOR;
		}
		
		int[] parts = parseStringPattern(pattern);
			
		return new PatternDataOptions.Builder()
				.parts(parts)
				.connector(connector)
				.allCapital(allCapital)
				.build();
	}
	
	private int[] parseStringPattern(String pattern) {
		String[] patternParts = pattern.split(PatternDataOptions.SPLITERATOR);
		int length = patternParts.length;
		int[] parts = new int[length];

		for (int i = 0; i < length; i++) {
			try {
				parts[i] = Integer.parseInt(patternParts[i]);
			} catch (NumberFormatException e){
				throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
			}
		}
		
		return parts;
	}

}
