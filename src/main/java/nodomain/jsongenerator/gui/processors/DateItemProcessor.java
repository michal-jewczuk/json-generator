package nodomain.jsongenerator.gui.processors;

import java.time.LocalDate;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.DateDataOptions;
import nodomain.jsongenerator.gui.domain.Element;

public enum DateItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(DataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		LocalDate lowerBound = LocalDate.parse(((TextField) pane.getChildren().get(3)).getText());
		LocalDate upperBound = LocalDate.parse(((TextField) pane.getChildren().get(5)).getText());
		String pattern = ((TextField) pane.getChildren().get(7)).getText();
		
		DateDataOptions options = new DateDataOptions(lowerBound, upperBound, pattern);
		element.setOptions(options);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		DateDataOptions datao = (DateDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append("\"").append(datao.getLowerBound()).append("\"").append(",");
		sb.append("\"upper_bound\": ").append("\"").append(datao.getUpperBound()).append("\"").append(",");
		sb.append("\"output_pattern\": ").append("\"").append(datao.getOutputPattern()).append("\"");
		sb.append("}");
		
		return sb;
	}

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		LocalDate lowerBound = LocalDate.parse(((TextField) pane.getChildren().get(3)).getText());
		LocalDate upperBound = LocalDate.parse(((TextField) pane.getChildren().get(5)).getText());
		String pattern = ((TextField) pane.getChildren().get(7)).getText();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append("\"").append(lowerBound).append("\"").append(",");
		sb.append("\"upper_bound\": ").append("\"").append(upperBound).append("\"").append(",");
		sb.append("\"output_pattern\": ").append("\"").append(pattern).append("\"");
		sb.append("}");
		
		return sb;
	}

}
