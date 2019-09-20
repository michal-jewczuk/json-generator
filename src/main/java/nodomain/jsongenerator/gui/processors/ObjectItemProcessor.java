package nodomain.jsongenerator.gui.processors;

import org.json.JSONObject;

import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.ObjectDataOptions;
import nodomain.jsongenerator.gui.domain.Element;

public enum ObjectItemProcessor implements ItemProcessor {
	
	INSTANCE;

	@Override
	public Element fromGuiToElement(DataType type, GridPane pane) {
		Element element = createGenericElement(type, pane);
		
		int count = Integer.valueOf(((TextField) pane.getChildren().get(3)).getText());
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
		ObjectDataOptions options = new ObjectDataOptions(obj.getJSONArray("structure"), count);
		element.setOptions(options);
		
		return element;
	}

	@Override
	public StringBuilder returnOptionsAsString(DataOptions options) {
		ObjectDataOptions datao = (ObjectDataOptions) options;
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"count\": ").append("\"").append(datao.getCount()).append("\",");
		sb.append("\"structure\": ").append(datao.getStructure());
		sb.append("}");
		
		return sb;
	}

}
