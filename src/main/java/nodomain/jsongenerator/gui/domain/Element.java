package nodomain.jsongenerator.gui.domain;

import nodomain.jsongenerator.data.DataOptions;

public class Element {

	GUIDataType type;
	String name;
	DataOptions options;
	
	public Element() {
		
	}

	public GUIDataType getType() {
		return type;
	}

	public void setType(GUIDataType type) {
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
