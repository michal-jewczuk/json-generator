package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.gui.domain.Element;

public interface ItemProcessor {
	
	Element fromGuiToElement(DataType type, GridPane pane);
	StringBuilder returnOptionsAsString(DataOptions options);
	StringBuilder extractOptionsFromGUI(GridPane pane);

	default StringBuilder fromGuiToJSON(DataType type, GridPane pane) {
		StringBuilder sb = createHeader(type, extractName(pane));
		sb.append(extractOptionsFromGUI(pane));	
		sb.append("}");
		
		return sb;
	}
	
	default Element createGenericElement(DataType type, GridPane pane) {
		Element element = new Element();
		element.setType(type);
		element.setName(extractName(pane));
		return element;
	}
	
	default String extractName(GridPane pane) {	
		TextField tf = (TextField) pane.getChildren().get(1);	
		return tf.getText();
	}
	
	default StringBuilder fromElementToJSON(Element element) {
		StringBuilder sb = createHeader(element);
		sb.append(returnOptionsAsString(element.getOptions()));
		
		sb.append("}");
		return sb;
	}
	
	default StringBuilder createHeader(Element element) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"type\": ").append("\"").append(element.getType()).append("\",");
		sb.append("\"name\": ").append("\"").append(element.getName()).append("\",");
		sb.append("\"options\": ");
		
		return sb;
	}
	
	default StringBuilder createHeader(DataType type, String name) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"type\": ").append("\"").append(type).append("\",");
		sb.append("\"name\": ").append("\"").append(name).append("\",");
		sb.append("\"options\": ");
		
		return sb;
	}
}
