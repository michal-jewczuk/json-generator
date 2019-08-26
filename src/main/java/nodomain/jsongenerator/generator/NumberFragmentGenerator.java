package nodomain.jsongenerator.generator;

import java.util.Random;
import java.util.stream.LongStream;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, DataOptions options) {
		NumberDataOptions opt = (NumberDataOptions) options;
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateNumber(opt));

		return fragment;
	}
	
	private long generateNumber(NumberDataOptions options) {
		Random rand = new Random();
		LongStream str = rand.longs(1, options.getNumberMin(), options.getNumberMax() + 1);
		
		return str.sum();
	}

}
