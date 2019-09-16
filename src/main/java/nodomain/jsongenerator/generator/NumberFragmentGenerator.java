package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.data.parsers.NumberDataOptionsParser;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		NumberDataOptions options = 
				NumberDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateNumber(options));

		return fragment;
	}
	
	private long generateNumber(NumberDataOptions options) {
		if (options.getLowerBound() == options.getUpperBound()) {
			return options.getLowerBound();
		}
		
		return rnd.longs(1, options.getLowerBound(), options.getUpperBound()).sum();
	}

}
