package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.gui.domain.Element;
import nodomain.jsongenerator.gui.domain.GUIDataType;

public enum BoolItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(GUIDataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		boolean onlyTrue = ((CheckBox) pane.getChildren().get(2)).isSelected();
		boolean onlyFalse = ((CheckBox) pane.getChildren().get(3)).isSelected();
		element.setOptions(new BoolDataOptions(onlyTrue, onlyFalse));
		
		return element;
	}

	@Override
	public StringBuilder fromElementToJSON(Element element) {
		StringBuilder sb = createHeader(element);
		sb.append(returnOptionsAsString(element.getOptions()));
		
		sb.append("}");
		return sb;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		BoolDataOptions datao = (BoolDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"only_true\": ").append(datao.isOnlyTrue()).append(",");
		sb.append("\"only_false\": ").append(datao.isOnlyFalse());
		sb.append("}");
		return sb;
	}



}
