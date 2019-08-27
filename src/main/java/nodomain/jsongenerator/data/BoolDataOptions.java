package nodomain.jsongenerator.data;

public class BoolDataOptions extends DataOptions {

	private boolean onlyTrue;
	private boolean onlyFalse;

	public BoolDataOptions() {

	}

	public BoolDataOptions(boolean onlyTrue, boolean onlyFalse) {
		this.onlyTrue = onlyTrue;
		this.onlyFalse = onlyFalse;
	}

	public boolean isOnlyTrue() {
		return onlyTrue;
	}

	public void setOnlyTrue(boolean onlyTrue) {
		this.onlyTrue = onlyTrue;
	}

	public boolean isOnlyFalse() {
		return onlyFalse;
	}

	public void setOnlyFalse(boolean onlyFalse) {
		this.onlyFalse = onlyFalse;
	}

}
