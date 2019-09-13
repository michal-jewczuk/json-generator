package nodomain.jsongenerator.data;

public class PatternDataOptions extends DataOptions {
	
	public static final String SPLITERATOR = "-";

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
		
		public Builder parts(String pattern) {	
			this.parts = parsePatternToParts(pattern);
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
		
		private int[] parsePatternToParts(String pattern) {
			String[] patternParts = pattern.split(SPLITERATOR);
			int length = patternParts.length;
			int[] parts = new int[length];
			for (int i = 0; i < length; i++) {
				parts[i] = Integer.parseInt(patternParts[i]);
			}
			
			return parts;
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
	
	public String getPattern() {
		StringBuilder sb = new StringBuilder();
		for (int part: parts) {
			sb.append(part).append(SPLITERATOR);
		}
		return sb.toString();
	}

}
