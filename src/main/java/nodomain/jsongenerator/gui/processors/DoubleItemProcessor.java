package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public enum DoubleItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String lowerBound = ((TextField) pane.getChildren().get(3)).getText();
		String upperBound = ((TextField) pane.getChildren().get(5)).getText();
		String precision = ((TextField) pane.getChildren().get(7)).getText();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"lower_bound\": ").append(lowerBound).append(",");
		sb.append("\"upper_bound\": ").append(upperBound).append(",");
		sb.append("\"precision\": ").append(precision);
		sb.append("}");
		
		return sb;
	}

}
