package nodomain.jsongenerator.data;

public class StringDataOptions extends DataOptions {

	private int length;
	private boolean firstCapital;
	private boolean allCapital;
	
	public static class Builder {
		private int length;
		private boolean firstCapital;
		private boolean allCapital;
		
		public Builder length(int length) {
			this.length = length;
			return this;
		}
		
		public Builder firstCapital(boolean firstCapital) {
			this.firstCapital = firstCapital;
			return this;
		}		
		
		public Builder allCapital(boolean allCapital) {
			this.allCapital = allCapital;
			return this;
		}		
		public StringDataOptions build() {
			return new StringDataOptions(this);
		}
	}
	
	private StringDataOptions(Builder builder) {
		length = builder.length;
		firstCapital = builder.firstCapital;
		allCapital = builder.allCapital;
	}
	
	public StringDataOptions() {
		
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isFirstCapital() {
		return firstCapital;
	}

	public void setFirstCapital(boolean firstCapital) {
		this.firstCapital = firstCapital;
	}

	public boolean isAllCapital() {
		return allCapital;
	}

	public void setAllCapital(boolean allCapital) {
		this.allCapital = allCapital;
	}
	
}
