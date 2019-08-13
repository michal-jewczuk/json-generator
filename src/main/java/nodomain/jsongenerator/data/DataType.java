package nodomain.jsongenerator.data;

import nodomain.jsongenerator.generator.NumberFragmentGenerator;
import nodomain.jsongenerator.generator.StringFragmentGenerator;

public enum DataType {

	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(String name, DataOptions options) {
			return StringFragmentGenerator.INSTANCE.generateFragment(name, options);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(String name, DataOptions options) {
			return NumberFragmentGenerator.INSTANCE.generateFragment(name, options);
		}
	};
	
	public abstract StringBuilder createJsonFragment(String name, DataOptions options);
}
