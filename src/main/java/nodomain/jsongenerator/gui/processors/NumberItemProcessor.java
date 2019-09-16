package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.NumberDataOptions;
import nodomain.jsongenerator.gui.domain.Element;
import nodomain.jsongenerator.gui.domain.GUIDataType;

public enum NumberItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(GUIDataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		int lowerBound = Integer.valueOf(((TextField) pane.getChildren().get(3)).getText());
		int upperBound = Integer.valueOf(((TextField) pane.getChildren().get(5)).getText());
		NumberDataOptions options = new NumberDataOptions(lowerBound, upperBound);
		element.setOptions(options);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		NumberDataOptions datao = (NumberDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append(datao.getLowerBound()).append(",");
		sb.append("\"upper_bound\": ").append(datao.getUpperBound());
		sb.append("}");
		
		return sb;
	}

}
