package nodomain.jsongenerator.generator;

import org.json.JSONObject;

import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.data.parsers.ObjectDataOptionsParser;
import nodomain.jsongenerator.main.JsonGenerator;

public enum ObjectFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		ObjectDataOptions options = 
				ObjectDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append(generateObjectFragment(options));
		
		return fragment;
	}

	private StringBuilder generateObjectFragment(ObjectDataOptions options) {
		return JsonGenerator.generateJson(options.getCount(), options.getStructure(), true);
	}

}
