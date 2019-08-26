package nodomain.jsongenerator.generator;

import java.util.Random;
import java.util.stream.LongStream;

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
		Random rand = new Random();
		LongStream str = rand.longs(1, options.getNumberMin(), options.getNumberMax() + 1);
		
		return str.sum();
	}

}
