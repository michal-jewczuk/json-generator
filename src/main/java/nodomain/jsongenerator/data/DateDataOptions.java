package nodomain.jsongenerator.data;

import java.time.LocalDate;

public class DateDataOptions extends DataOptions {

	private LocalDate lowerBound;
	private LocalDate upperBound;
	private String outputPattern;

	public DateDataOptions() {

	}

	public DateDataOptions(LocalDate lowerBound, LocalDate upperBound, String outputPattern) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.outputPattern = outputPattern;
	}

	public LocalDate getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(LocalDate lowerBound) {
		this.lowerBound = lowerBound;
	}

	public LocalDate getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(LocalDate upperBound) {
		this.upperBound = upperBound;
	}

	public String getOutputPattern() {
		return outputPattern;
	}

	public void setOutputPattern(String outputPattern) {
		this.outputPattern = outputPattern;
	}

}
