package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum NumberDataOptionsParser implements DataOptionsParser {

	INSTANCE;

	@Override
	public NumberDataOptions parseDataOptions(JSONObject options) {
		long lowerBound;
		long upperBound;
		
		if (options == null) {
			return new NumberDataOptions(0, 0);
		}
		
		try {
			lowerBound = options.getLong("lower_bound");
			upperBound = options.getLong("upper_bound");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (upperBound < lowerBound) {
			throw new IllegalArgumentException("Number max < min!");
		}
		
		return new NumberDataOptions(lowerBound, upperBound);
	}
	
}
