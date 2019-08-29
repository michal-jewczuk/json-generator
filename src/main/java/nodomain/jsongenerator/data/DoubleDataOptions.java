package nodomain.jsongenerator.data;

public class DoubleDataOptions extends DataOptions {

	private double lowerBound;
	private double upperBound;
	private int precision;

	public DoubleDataOptions() {

	}

	public DoubleDataOptions(double lowerBound, double upperBound, int precision) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.precision = precision;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

}
