package nodomain.jsongenerator.generator;

import java.util.Random;
import java.util.stream.LongStream;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	public StringBuilder generateFragment(String name, DataOptions options) {
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateNumber(options));

		return fragment;
	}
	
	private long generateNumber(DataOptions options) {
		NumberDataOptions opt = (NumberDataOptions) options;
		Random rand = new Random();
		LongStream str = rand.longs(1, opt.getNumberMin(), opt.getNumberMax());
		
		return str.sum();
	}

}
