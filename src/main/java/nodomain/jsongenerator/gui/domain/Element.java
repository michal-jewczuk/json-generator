package nodomain.jsongenerator.gui.domain;

import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;

public class Element {

	DataType type;
	String name;
	DataOptions options;
	
	public Element() {
		
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataOptions getOptions() {
		return options;
	}

	public void setOptions(DataOptions options) {
		this.options = options;
	}
	
}
