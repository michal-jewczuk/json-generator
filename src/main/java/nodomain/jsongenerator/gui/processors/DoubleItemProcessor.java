package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DoubleDataOptions;
import nodomain.jsongenerator.gui.domain.Element;
import nodomain.jsongenerator.gui.domain.GUIDataType;

public enum DoubleItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(GUIDataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		double lowerBound = Double.valueOf(((TextField) pane.getChildren().get(3)).getText());
		double upperBound = Double.valueOf(((TextField) pane.getChildren().get(5)).getText());
		int precision = Integer.valueOf(((TextField) pane.getChildren().get(7)).getText());
		
		DoubleDataOptions options = new DoubleDataOptions(lowerBound, upperBound, precision);
		element.setOptions(options);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		DoubleDataOptions datao = (DoubleDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append(datao.getLowerBound()).append(",");
		sb.append("\"upper_bound\": ").append(datao.getUpperBound()).append(",");
		sb.append("\"precision\": ").append(datao.getPrecision());
		sb.append("}");
		
		return sb;
	}

}
