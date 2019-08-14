package nodomain.jsongenerator.data;

public class NumberDataOptions extends DataOptions {

	private long numberMin;
	private long numberMax;

	public NumberDataOptions(long numberMin, long numberMax) {
		this.numberMin = numberMin;
		this.numberMax = numberMax;
	}

	public long getNumberMin() {
		return numberMin;
	}

	public void setNumberMin(long numberMin) {
		this.numberMin = numberMin;
	}

	public long getNumberMax() {
		return numberMax;
	}

	public void setNumberMax(long numberMax) {
		this.numberMax = numberMax;
	}

}
