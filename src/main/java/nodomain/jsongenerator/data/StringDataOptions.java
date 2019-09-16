package nodomain.jsongenerator.data;

public class StringDataOptions extends DataOptions {

	private int minLength;
	private int maxLength;
	private boolean firstCapital;
	private boolean allCapital;
	
	public static class Builder {
		private int minLength;
		private int maxLength;
		private boolean firstCapital;
		private boolean allCapital;
		
		public Builder minLength(int minLength) {
			this.minLength = minLength;
			return this;
		}
		
		public Builder maxLength(int maxLength) {
			this.maxLength = maxLength;
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
		minLength = builder.minLength;
		maxLength = builder.maxLength;
		firstCapital = builder.firstCapital;
		allCapital = builder.allCapital;
	}
	
	public StringDataOptions() {
		
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

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
}
