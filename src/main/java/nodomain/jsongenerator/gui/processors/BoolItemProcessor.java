package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public enum BoolItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		boolean onlyTrue = ((CheckBox) pane.getChildren().get(2)).isSelected();
		boolean onlyFalse = ((CheckBox) pane.getChildren().get(3)).isSelected();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"only_true\": ").append(onlyTrue).append(",");
		sb.append("\"only_false\": ").append(onlyFalse);
		sb.append("}");
		return sb;
	}

}
