package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.gui.domain.Element;

public enum PatternItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(DataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		String pattern = ((TextField) pane.getChildren().get(3)).getText();
		String connector = ((TextField) pane.getChildren().get(5)).getText();
		boolean allCaps = ((CheckBox) pane.getChildren().get(6)).isSelected();
		
		PatternDataOptions pto = new PatternDataOptions.Builder()
				.parts(pattern)
				.connector(connector)
				.allCapital(allCaps)
				.build();
		element.setOptions(pto);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		PatternDataOptions datao = (PatternDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"pattern\": ").append("\"").append(datao.getPattern()).append("\",");
		sb.append("\"connector\": ").append("\"").append(datao.getConnector()).append("\",");
		sb.append("\"all_cap\": ").append(datao.isAllCapital());
		sb.append("}");
		return sb;
	}

}
