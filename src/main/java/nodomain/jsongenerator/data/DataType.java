package nodomain.jsongenerator.data;

import org.json.JSONObject;

import nodomain.jsongenerator.data.parsers.NumberDataOptionsParser;
import nodomain.jsongenerator.data.parsers.StringDataOptionsParser;
import nodomain.jsongenerator.generator.NumberFragmentGenerator;
import nodomain.jsongenerator.generator.StringFragmentGenerator;

public enum DataType {

	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			DataOptions options = StringDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
			return StringFragmentGenerator.INSTANCE.generateFragment(name, options);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			DataOptions options = NumberDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
			return NumberFragmentGenerator.INSTANCE.generateFragment(name, options);
		}
	};
	
	public abstract StringBuilder createJsonFragment(String name, JSONObject dataOptions);
	
}
