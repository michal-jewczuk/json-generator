package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.parsers.BoolDataOptionsParser;

public enum BoolFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		BoolDataOptions options = 
				BoolDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateBoolean(options));

		return fragment;
	}

	private boolean generateBoolean(BoolDataOptions options) {
	
		if (options.isOnlyTrue()) {
			return true;
		} else if (options.isOnlyFalse()) {
			return false;
		} else {
			return rnd.nextBoolean();
		}
	}

}
