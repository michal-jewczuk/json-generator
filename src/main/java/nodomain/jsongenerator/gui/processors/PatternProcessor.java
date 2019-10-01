package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.processors.common.ItemProcessor;

public enum PatternProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String pattern = ((TextField) pane.getChildren().get(3)).getText();
		String connector = ((TextField) pane.getChildren().get(5)).getText();
		boolean allCaps = ((CheckBox) pane.getChildren().get(6)).isSelected();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"pattern\": ").append("\"").append(pattern).append("\",");
		sb.append("\"connector\": ").append("\"").append(connector).append("\",");
		sb.append("\"all_cap\": ").append(allCaps);
		sb.append("}");
		return sb;
	}

}
