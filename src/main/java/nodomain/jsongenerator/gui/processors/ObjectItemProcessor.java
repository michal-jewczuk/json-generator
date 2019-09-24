package nodomain.jsongenerator.gui.processors;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataType;

public enum ObjectItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public StringBuilder extractOptionsFromGUI(GridPane pane) {
		String count = ((TextField) pane.getChildren().get(3)).getText();
		
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"count\": ").append(count).append(",");
		sb.append("\"structure\": ").append(extractStructure(pane));
		sb.append("}");
		
		return sb;
	}
	
	private JSONArray extractStructure(GridPane pane) {
		Accordion acc = (Accordion) pane.getChildren().get(6);
		StringBuilder sb = new StringBuilder("{\"structure\": [");
		
		if (acc.getPanes().size() > 0) {
			for (TitledPane currentP: acc.getPanes()) {
				DataType currentT = DataType.valueOf(MainProcessor.INSTANCE.getType(currentP.getText()));
				sb.append(currentT.convertToJSON(MainProcessor.INSTANCE.getObjectOptions(currentP))).append(",");
			}
			sb.setCharAt(sb.length() - 1, ']');
		} else {
			sb.append("]");
		}
		sb.append("}");
		
		JSONObject obj = new JSONObject(sb.toString());	
		return obj.getJSONArray("structure");
	}

}
