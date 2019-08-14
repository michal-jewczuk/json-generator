package nodomain.jsongenerator.data.parsers;

import org.json.JSONObject;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;

public enum NumberDataOptionsParser implements DataOptionsParser {

	INSTANCE;

	@Override
	public DataOptions parseDataOptions(JSONObject options) {
		long numberMin = options.getLong("number_min");
		long numberMax = options.getLong("number_max");
		
		if (numberMax < numberMin) {
			throw new IllegalArgumentException("Number max < min!");
		}
		
		return new NumberDataOptions(numberMin, numberMax);
	}
	
}
