package nodomain.jsongenerator.data.parsers;

import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum BoolDataOptionsParser implements DataOptionsParser {
	
	INSTANCE;

	@Override
	public BoolDataOptions parseDataOptions(JSONObject options) {
		boolean onlyTrue;
		boolean onlyFalse;
		
		try {
			onlyTrue = options.getBoolean("only_true");
			onlyFalse = options.getBoolean("only_false");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (onlyTrue && onlyFalse) {
			throw new IllegalArgumentException("Only True and only False set to true");
		}
		
		return new BoolDataOptions(onlyTrue, onlyFalse);
	}

}
