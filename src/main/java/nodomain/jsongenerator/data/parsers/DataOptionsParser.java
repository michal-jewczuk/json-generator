package nodomain.jsongenerator.data.parsers;

import org.json.JSONObject;

import nodomain.jsongenerator.data.DataOptions;

public interface DataOptionsParser {

	DataOptions parseDataOptions(JSONObject options);
}
