package nodomain.jsongenerator.gui.generators;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import nodomain.jsongenerator.data.DataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.parsers.BoolDataOptionsParser;
import nodomain.jsongenerator.data.parsers.DateDataOptionsParser;
import nodomain.jsongenerator.data.parsers.DoubleDataOptionsParser;
import nodomain.jsongenerator.data.parsers.NumberDataOptionsParser;
import nodomain.jsongenerator.data.parsers.ObjectDataOptionsParser;
import nodomain.jsongenerator.data.parsers.PatternDataOptionsParser;
import nodomain.jsongenerator.data.parsers.StringDataOptionsParser;

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
		Node node = null;
		DataOptions options = null;
		
		switch (type) {
			case JSON_STRING:
				options = StringDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonString(name, options);
				break;
			case JSON_NUMBER:
				options = NumberDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonNumber(name, options);
				break;
			case JSON_PATTERN:
				options = PatternDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonPattern(name, options);
				break;
			case JSON_BOOL:
				options = BoolDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonBool(name, options);
				break;
			case JSON_DOUBLE:
				options = DoubleDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonDouble(name, options);
				break;
			case JSON_DATE:
				options = DateDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonDate(name, options);
				break;
			case JSON_OBJECT:
				options = ObjectDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonObject(name, options);
				break;
			default:
				node = null;
		}
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
