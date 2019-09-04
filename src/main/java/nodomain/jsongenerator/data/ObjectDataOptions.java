package nodomain.jsongenerator.data;

import org.json.JSONArray;

public class ObjectDataOptions extends DataOptions {

	private JSONArray structure;
	private int count;

	public ObjectDataOptions() {

	}

	public ObjectDataOptions(JSONArray structure, int count) {
		this.structure = structure;
		this.count = count;
	}

	public JSONArray getStructure() {
		return structure;
	}

	public void setStructure(JSONArray structure) {
		this.structure = structure;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
