package nodomain.jsongenerator.data;

import nodomain.jsongenerator.generator.NumberFragmentGenerator;
import nodomain.jsongenerator.generator.StringFragmentGenerator;

public enum DataType {

	JSON_STRING {
		@Override
		public StringBuilder createJsonFragment(DataOptions options) {
			return StringFragmentGenerator.INSTANCE.generateFragment(options);
		}
	},
	JSON_NUMBER {
		@Override
		public StringBuilder createJsonFragment(DataOptions options) {
			return NumberFragmentGenerator.INSTANCE.generateFragment(options);
		}
	};
	
	public abstract StringBuilder createJsonFragment(DataOptions options);
}
