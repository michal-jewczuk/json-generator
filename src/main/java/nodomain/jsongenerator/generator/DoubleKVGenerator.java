package nodomain.jsongenerator.generator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.json.JSONObject;

public enum DoubleKVGenerator implements KVGenerator {
	
	INSTANCE;
	
	private double lowerBound;
	private double upperBound;
	private int precision;

	@Override
	public StringBuilder generateKeyValue(String name, JSONObject options) {
		lowerBound = options.getDouble("lower_bound");
		upperBound = options.getDouble("upper_bound");
		precision = options.getInt("precision");
		
		StringBuilder fragment = generateKey(name);
		fragment.append(generateValue());
		
		return fragment;
	}

	private BigDecimal generateValue() {
		double result = rnd.doubles(1L, lowerBound, upperBound).sum();
		BigDecimal bd = BigDecimal.valueOf(result);
		bd = bd.setScale(precision, RoundingMode.DOWN);
		
		return bd;
	}

}
