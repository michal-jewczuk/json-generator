package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataType;

public enum MainProcessor {

	INSTANCE;
	
	public StringBuilder proccessStructure(Accordion acc) {
		StringBuilder sb = new StringBuilder("{\"types\": [");
		for (TitledPane pane: acc.getPanes()) {
			DataType type = DataType.valueOf(getType(pane.getText()));
			sb.append(type.convertToJSON(getObjectOptions(pane))).append(",");
		}
		sb.setCharAt(sb.length() - 1, ']');
		sb.append("}");
		return sb;
	}
	
	public String getType(String title) {
		String[] tmp = title.split("\\[");
		String type = tmp[1].substring(0, tmp[1].length() - 1);
		return type;
	}
	
	public GridPane getObjectOptions(TitledPane pane) {
		BorderPane bp = (BorderPane) pane.getContent();
		return (GridPane) bp.getCenter();
	}
}
