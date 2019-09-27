package nodomain.jsongenerator.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;

public enum DoubleFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	private double lowerBound;
	private double upperBound;
	private int precision;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		lowerBound = options.getDouble("lower_bound");
		upperBound = options.getDouble("upper_bound");
		precision = options.getInt("precision");
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateDoubleFragment());
		
		return fragment;
	}

	private BigDecimal generateDoubleFragment() {
		double result = rnd.doubles(1L, lowerBound, upperBound).sum();
		BigDecimal bd = BigDecimal.valueOf(result);
		bd = bd.setScale(precision, RoundingMode.DOWN);
		
		return bd;
	}

}
