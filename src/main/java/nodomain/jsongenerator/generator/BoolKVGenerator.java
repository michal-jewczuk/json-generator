package nodomain.jsongenerator.generator;

import org.json.JSONObject;

public enum BoolKVGenerator implements KVGenerator {
	
	INSTANCE;
	
	private boolean onlyTrue;
	private boolean onlyFalse;

	@Override
	public StringBuilder generateKeyValue(String name, JSONObject options) {
		onlyTrue = options.getBoolean("only_true");
		onlyFalse = options.getBoolean("only_false");
		
		StringBuilder fragment = generateKey(name);
		fragment.append(generateValue());

		return fragment;
	}

	private boolean generateValue() {
	
		if (onlyTrue) {
			return true;
		} else if (onlyFalse) {
			return false;
		} else {
			return rnd.nextBoolean();
		}
	}

}
