package nodomain.jsongenerator.generator;

import org.json.JSONObject;

public enum NumberFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	private long lowerBound;
	private long upperBound;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		lowerBound = options.getLong("lower_bound");
		upperBound = options.getLong("upper_bound");
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateNumber());

		return fragment;
	}
	
	private long generateNumber() {
		if (lowerBound == upperBound) {
			return lowerBound;
		}
		
		return rnd.longs(1, lowerBound, upperBound).sum();
	}

}
