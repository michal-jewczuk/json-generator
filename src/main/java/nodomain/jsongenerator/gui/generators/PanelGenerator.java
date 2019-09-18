package nodomain.jsongenerator.gui.generators;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import nodomain.jsongenerator.data.DataType;

public enum PanelGenerator {
	
	INSTANCE;
	
    public List<TitledPane> createStructurePanes(JSONArray objects) {
		List<TitledPane> panes = new ArrayList<>();

		int length = objects.length();
		for (int i = 0; i < length; i++) {
			JSONObject current = objects.getJSONObject(i);		
			TitledPane pane = createSinglePane(current);
			panes.add(pane);
		}
		
		return panes;
	}
	
    public TitledPane createSinglePane(JSONObject obj) {
		final String tileName = createTileName(obj);
		final Node node = createNode(obj);
		
		return new TitledPane(tileName,node);	
    }
    
	private Node createNode(JSONObject current) {
		DataType type = DataType.valueOf(current.getString("type"));
		String name = current.getString("name");
		JSONObject rawOptions = current.getJSONObject("options");
		Node node = type.createGUIElement(name, rawOptions, true);

		return node;
	}

	private String createTileName(JSONObject object) {
		StringBuilder sb = new StringBuilder(object.getString("name"));
		sb.append(" [");
		sb.append(object.getString("type"));
		sb.append("]");
		return sb.toString();
	}

}
