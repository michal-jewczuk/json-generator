package nodomain.jsongenerator.data;

public class PatternDataOptions extends DataOptions {

	private int[] parts;
	private String connector;
	private boolean allCapital;

	public static class Builder {
		private int[] parts;
		private String connector;
		private boolean allCapital;

		public Builder() {

		}

		public Builder parts(int[] parts) {
			this.parts = parts;
			return this;
		}

		public Builder connector(String connector) {
			this.connector = connector;
			return this;
		}

		public Builder allCapital(boolean allCapital) {
			this.allCapital = allCapital;
			return this;
		}

		public PatternDataOptions build() {
			return new PatternDataOptions(this);
		}

	}

	private PatternDataOptions(Builder builder) {
		parts = builder.parts;
		connector = builder.connector;
		allCapital = builder.allCapital;
	}

	public PatternDataOptions() {

	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public boolean isAllCapital() {
		return allCapital;
	}

	public void setAllCapital(boolean allCapital) {
		this.allCapital = allCapital;
	}

	public int[] getParts() {
		return parts;
	}

	public void setParts(int[] parts) {
		this.parts = parts;
	}

}
