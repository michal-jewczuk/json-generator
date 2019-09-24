package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public enum DateItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String lowerBound = ((TextField) pane.getChildren().get(3)).getText();
		String upperBound = ((TextField) pane.getChildren().get(5)).getText();
		String pattern = ((TextField) pane.getChildren().get(7)).getText();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append("\"").append(lowerBound).append("\"").append(",");
		sb.append("\"upper_bound\": ").append("\"").append(upperBound).append("\"").append(",");
		sb.append("\"output_pattern\": ").append("\"").append(pattern).append("\"");
		sb.append("}");
		
		return sb;
	}

}
