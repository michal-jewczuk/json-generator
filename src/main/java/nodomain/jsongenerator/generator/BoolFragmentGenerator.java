package nodomain.jsongenerator.generator;

import org.json.JSONObject;

public enum BoolFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	private boolean onlyTrue;
	private boolean onlyFalse;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		onlyTrue = options.getBoolean("only_true");
		onlyFalse = options.getBoolean("only_false");
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateBoolean());

		return fragment;
	}

	private boolean generateBoolean() {
	
		if (onlyTrue) {
			return true;
		} else if (onlyFalse) {
			return false;
		} else {
			return rnd.nextBoolean();
		}
	}

}
