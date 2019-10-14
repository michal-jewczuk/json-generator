package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.processors.common.ItemProcessor;

public enum BoolProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {	
		boolean onlyTrue = ((RadioButton) pane.getChildren().get(3)).isSelected();
		boolean onlyFalse = ((RadioButton) pane.getChildren().get(4)).isSelected();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"only_true\": ").append(onlyTrue).append(",");
		sb.append("\"only_false\": ").append(onlyFalse);
		sb.append("}");
		return sb;
	}

}
