package nodomain.jsongenerator.generator;

import org.json.JSONObject;

public enum NumberKVGenerator implements KVGenerator {
	
	INSTANCE;
	
	private long lowerBound;
	private long upperBound;

	@Override
	public StringBuilder generateKeyValue(String name, JSONObject options) {
		lowerBound = options.getLong("lower_bound");
		upperBound = options.getLong("upper_bound");
		
		StringBuilder fragment = generateKey(name);
		fragment.append(generateValue());

		return fragment;
	}
	
	private long generateValue() {
		if (lowerBound == upperBound) {
			return lowerBound;
		}
		
		return rnd.longs(1, lowerBound, upperBound).sum();
	}

}
