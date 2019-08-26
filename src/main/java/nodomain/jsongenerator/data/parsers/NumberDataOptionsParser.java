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
		long numberMin;
		long numberMax;
		try {
			numberMin = options.getLong("number_min");
			numberMax = options.getLong("number_max");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (numberMax < numberMin) {
			throw new IllegalArgumentException("Number max < min!");
		}
		
		return new NumberDataOptions(numberMin, numberMax);
	}
	
}
