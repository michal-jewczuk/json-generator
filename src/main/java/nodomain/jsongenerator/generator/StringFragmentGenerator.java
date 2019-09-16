package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.StringDataOptions;
import nodomain.jsongenerator.data.parsers.StringDataOptionsParser;
import nodomain.jsongenerator.util.StringUtil;

public enum StringFragmentGenerator implements FragmentGenerator {

	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		StringDataOptions options = 
				StringDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
			
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generateStringFragment(options));
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generateStringFragment(StringDataOptions options) {
		
		StringBuilder fragment = new StringBuilder();
		String generated = StringUtil.generateStringFragment(generateLength(options), AppConfig.STRING_SYMBOLS);
	
		if (options.isAllCapital()) {
			fragment = new StringBuilder(generated.toUpperCase());
		} else {
			if (options.isFirstCapital()) {
				fragment = StringUtil.capitalizeFirstLetter(generated);
			} else {
				fragment = new StringBuilder(generated);
			}
		}
		
		return fragment;
	}

	private int generateLength(StringDataOptions options) {
		if (options.getMinLength() == options.getMaxLength()) {
			return options.getMinLength();
		}
		
		return rnd.ints(1, options.getMinLength(), options.getMaxLength()).sum();
	}
	
}
