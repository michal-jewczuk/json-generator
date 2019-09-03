package nodomain.jsongenerator;

import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.main.JsonGenerator;

public class JsonGeneratorApplication {

	public static void main(String[] args) {
		
		int count = 1;
		String outputName = args.length < 2 ? AppConfig.DEFAULT_OUTPUT_NAME : args[1];
		
		try {
			count = Integer.parseInt(args[0]);
			if (count < 1) {
				count = 1;
			}
		} catch (Exception e) {
			
		}
		
		JsonGenerator.generateOutputFile(count, outputName);
	}
}
