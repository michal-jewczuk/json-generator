package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum StringDataOptionsParser implements DataOptionsParser {

	INSTANCE;

	@Override
	public StringDataOptions parseDataOptions(JSONObject options) {
		int minLength;
		int maxLength;
		boolean firstCapital;
		boolean allCapital;
		
		try {
			minLength = options.getInt("min_length");
			maxLength = options.getInt("max_length");
			firstCapital = options.getBoolean("first_cap");
			allCapital = options.getBoolean("all_cap");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (minLength < 1) {
			throw new IllegalArgumentException("Length < 1!");
		}
		
		if (maxLength < minLength) {
			throw new IllegalArgumentException("max < min!");
		}
		
		return new StringDataOptions.Builder()
				.minLength(minLength)
				.maxLength(maxLength)
				.firstCapital(firstCapital)
				.allCapital(allCapital)
				.build();
	}
	
	
}
