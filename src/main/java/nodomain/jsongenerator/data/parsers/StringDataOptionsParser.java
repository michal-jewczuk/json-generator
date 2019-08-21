package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum StringDataOptionsParser implements DataOptionsParser {

	INSTANCE;

	@Override
	public DataOptions parseDataOptions(JSONObject options) {
		int length;
		boolean firstCapital;
		boolean allCapital;
		
		try {
			length = options.getInt("length");
			firstCapital = options.getBoolean("first_cap");
			allCapital = options.getBoolean("all_cap");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (length < 1) {
			throw new IllegalArgumentException("Length < 1!");
		}
		
		return new StringDataOptions.Builder()
				.length(length)
				.firstCapital(firstCapital)
				.allCapital(allCapital)
				.build();
	}
	
	
}
