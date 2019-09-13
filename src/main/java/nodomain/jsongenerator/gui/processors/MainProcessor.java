package nodomain.jsongenerator.gui.processors;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.domain.GUIDataType;

public enum MainProcessor {

	INSTANCE;
	
	public void proccessStructure(Accordion acc) {
		for (TitledPane pane: acc.getPanes()) {
			GUIDataType type = GUIDataType.valueOf(getType(pane.getText()));
			StringBuilder sb = type.convertToJSON(getObjectOptions(pane));
			System.out.println(sb);
		}
	}
	
	private String getType(String title) {
		String[] tmp = title.split("\\[");
		String type = tmp[1].substring(0, tmp[1].length() - 1);
		return type;
	}
	
	private GridPane getObjectOptions(TitledPane pane) {
		BorderPane bp = (BorderPane) pane.getContent();
		return (GridPane) bp.getCenter();
	}
}
