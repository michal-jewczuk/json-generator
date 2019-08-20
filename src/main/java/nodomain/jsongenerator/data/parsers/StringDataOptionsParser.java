package nodomain.jsongenerator.data.parsers;

import org.json.JSONObject;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.StringDataOptions;

public enum StringDataOptionsParser implements DataOptionsParser {

	INSTANCE;

	@Override
	public DataOptions parseDataOptions(JSONObject options) {
		int length = options.getInt("length");
		boolean firstCapital = options.getBoolean("first_cap");
		boolean allCapital = options.getBoolean("all_cap");
		
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
