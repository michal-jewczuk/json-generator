package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.data.parsers.PatternDataOptionsParser;
import nodomain.jsongenerator.util.StringUtil;

public enum PatternFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		PatternDataOptions options = 
				PatternDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generatePatternFragment(options));
		fragment.append("\"");
		
		return fragment;
	}

	private StringBuilder generatePatternFragment(PatternDataOptions options) {
		
		StringBuilder fragment = new StringBuilder();
		
		String connect = "";
		for (int part : options.getParts()) {
			fragment.append(connect);
			fragment.append(StringUtil.generateStringFragment(part, AppConfig.STRING_SYMBOLS));
			connect = options.getConnector();
		}
		
		if (options.isAllCapital()) {
			fragment = new StringBuilder(fragment.toString().toUpperCase());
		}
		
		return fragment;
	}

}
