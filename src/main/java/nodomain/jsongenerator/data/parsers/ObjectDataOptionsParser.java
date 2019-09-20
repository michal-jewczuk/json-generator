package nodomain.jsongenerator.data.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.exceptions.JsonParsingException;

public enum ObjectDataOptionsParser implements DataOptionsParser {
	
	INSTANCE;

	@Override
	public ObjectDataOptions parseDataOptions(JSONObject options) {
		int count;
		JSONArray structure;
		
		if (options == null) {
			return new ObjectDataOptions(null, 1);
		}
		
		try {
			count = options.getInt("count");
			structure = options.getJSONArray("structure");
		} catch (JSONException e) {
			throw new JsonParsingException(AppConfig.ERROR_PARSING + e.getLocalizedMessage());
		}
		
		if (count < 0) {
			throw new IllegalArgumentException("Count < 0!");
		}
		
		return new ObjectDataOptions(structure, count);
	}

}
