package nodomain.jsongenerator.generator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.json.JSONObject;

import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.data.parsers.DateDataOptionsParser;

public enum DateFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;

	@Override
	public StringBuilder generateFragment(String name, JSONObject dataOptions) {
		DateDataOptions options = 
				DateDataOptionsParser.INSTANCE.parseDataOptions(dataOptions);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generateDateFragment(options));
		fragment.append("\"");
		
		return fragment;
	}

	private String generateDateFragment(DateDataOptions options) {
		LocalDate genDate;
		
		if (options.getLowerBound().equals(options.getUpperBound())) {
			genDate = options.getLowerBound();
		} else {
			long days = options.getUpperBound().toEpochDay() - options.getLowerBound().toEpochDay();
			long generated = new Random().longs(1, 0, days).sum();
			genDate = options.getLowerBound().plusDays(generated);
		}
		return genDate.format(DateTimeFormatter.ofPattern(options.getOutputPattern()));
	}

}
