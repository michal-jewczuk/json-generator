package nodomain.jsongenerator.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import org.json.JSONObject;

import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.data.parsers.DoubleDataOptionsParser;

public enum DoubleFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		DoubleDataOptions options = 
				DoubleDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateDoubleFragment(options));
		
		return fragment;
	}

	private BigDecimal generateDoubleFragment(DoubleDataOptions options) {
		Random rnd = new Random();
		double result = rnd.doubles(1L, options.getLowerBound(), options.getUpperBound()).sum();
		BigDecimal bd = BigDecimal.valueOf(result);
		bd = bd.setScale(options.getPrecision(), RoundingMode.DOWN);
		
		return bd;
	}

}
