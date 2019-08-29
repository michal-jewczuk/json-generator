package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum DoubleDataOptionsParser implements DataOptionsParser {
	
	INSTANCE;

	@Override
	public DoubleDataOptions parseDataOptions(JSONObject options) {
		double lowerBound;
		double upperBound;
		int precision;
		try {
			lowerBound = options.getDouble("lower_bound");
			upperBound = options.getDouble("upper_bound");
			precision = options.getInt("precision");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (upperBound < lowerBound) {
			throw new IllegalArgumentException("Number upper < lower!");
		}
		
		if (precision < 1) {
			throw new IllegalArgumentException("Precision < 1");
		}	
		
		return new DoubleDataOptions(lowerBound, upperBound, precision);
	}

}
