package nodomain.jsongenerator;

import nodomain.jsongenerator.main.JsonGenerator;

public class JsonGeneratorApplication {

	public static void main(String[] args) {
		
		int count = 1;
		
		try {
			count = Integer.parseInt(args[0]);
		} catch (Exception e) {
			
		}
		
		JsonGenerator.generateJson(count);
	}
}
