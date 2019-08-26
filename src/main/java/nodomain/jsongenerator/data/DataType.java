package nodomain.jsongenerator.data;

import org.json.JSONObject;

import nodomain.jsongenerator.generator.NumberFragmentGenerator;
import nodomain.jsongenerator.generator.PatternFragmentGenerator;
import nodomain.jsongenerator.generator.StringFragmentGenerator;

public enum DataType {

	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return StringFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return NumberFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}
	},
	JSON_PATTERN {
		@Override
		public StringBuilder createJsonFragment(String name, JSONObject dataOptions) {
			return PatternFragmentGenerator.INSTANCE.generateFragment(name, dataOptions);
		}		
	};
	
	public abstract StringBuilder createJsonFragment(String name, JSONObject dataOptions);
	
}
