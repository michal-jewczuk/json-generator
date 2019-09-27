package nodomain.jsongenerator.generator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public enum DateFragmentGenerator implements FragmentGenerator {
	
	INSTANCE;
	
	private LocalDate lowerBound;
	private LocalDate upperBound;
	private String outputPattern;

	@Override
	public StringBuilder generateFragment(String name, JSONObject options) {
		String lowerBoundString = options.getString("lower_bound");
		String upperBoundString = options.getString("upper_bound");
		outputPattern = options.getString("output_pattern");
		lowerBound = LocalDate.parse(lowerBoundString);
		upperBound = LocalDate.parse(upperBoundString);
		
		StringBuilder fragment = generateBegining(name);
		fragment.append("\"");
		fragment.append(generateDateFragment());
		fragment.append("\"");
		
		return fragment;
	}

	private String generateDateFragment() {
		LocalDate genDate;
		
		if (lowerBound.equals(upperBound)) {
			genDate = lowerBound;
		} else {
			long days = upperBound.toEpochDay() - lowerBound.toEpochDay();
			long generated = rnd.longs(1, 0, days).sum();
			genDate = lowerBound.plusDays(generated);
		}
		return genDate.format(DateTimeFormatter.ofPattern(outputPattern));
	}

}
