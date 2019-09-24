package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataType;

public interface ItemProcessor {
	
	StringBuilder extractOptionsFromGUI(GridPane pane);

	default StringBuilder fromGuiToJSON(DataType type, GridPane pane) {
		StringBuilder sb = createHeader(type, extractName(pane));
		sb.append(extractOptionsFromGUI(pane));	
		sb.append("}");
		
		return sb;
	}
	
	default String extractName(GridPane pane) {	
		TextField tf = (TextField) pane.getChildren().get(1);	
		return tf.getText();
	}
	
	default StringBuilder createHeader(DataType type, String name) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"type\": ").append("\"").append(type).append("\",");
		sb.append("\"name\": ").append("\"").append(name).append("\",");
		sb.append("\"options\": ");
		
		return sb;
	}
}
