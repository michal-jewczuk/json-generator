package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public enum StringItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String minLength = ((TextField) pane.getChildren().get(3)).getText();
		String maxLength = ((TextField) pane.getChildren().get(5)).getText();
		boolean firstCap = ((CheckBox) pane.getChildren().get(6)).isSelected();
		boolean allCaps = ((CheckBox) pane.getChildren().get(7)).isSelected();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"min_length\": ").append(minLength).append(",");
		sb.append("\"max_length\": ").append(maxLength).append(",");
		sb.append("\"first_cap\": ").append(firstCap).append(",");
		sb.append("\"all_cap\": ").append(allCaps);
		sb.append("}");
		
		return sb;
	}

}
