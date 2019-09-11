package nodomain.jsongenerator.gui.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.data.parsers.BoolDataOptionsParser;
import nodomain.jsongenerator.gui.StructureGenerator;
import nodomain.jsongenerator.main.JsonGenerator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
    public void initialize() {
    	structureFields.getPanes().addAll(createStructurePanes());
    }
	
	private List<TitledPane> createStructurePanes() {
		List<TitledPane> panes = new ArrayList<>();

		JSONArray objects = JsonGenerator.parseStructureFile();
		int length = objects.length();
		for (int i = 0; i < length; i++) {
			JSONObject current = objects.getJSONObject(i);

			final String tileName = createTileName(current);
			final Node node = createNode(current);
			
			TitledPane pane = new TitledPane(tileName,node);
			panes.add(pane);
		}
		
		return panes;
	}
	
	private Node createNode(JSONObject current) {
		DataType type = DataType.valueOf(current.getString("type"));
		String name = current.getString("name");
		JSONObject rawOptions = current.getJSONObject("options");
		Node node = null;
		
		switch (type) {
			case JSON_STRING:
				node = StructureGenerator.INSTANCE.createJsonString(name, null);
				break;
			case JSON_NUMBER:
				node = StructureGenerator.INSTANCE.createJsonNumber(name, null);
				break;
			case JSON_PATTERN:
				node = StructureGenerator.INSTANCE.createJsonPattern(name, null);
				break;
			case JSON_BOOL:
				BoolDataOptions options = BoolDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
				node = StructureGenerator.INSTANCE.createJsonBool(name, options);
				break;
			case JSON_DOUBLE:
				node = StructureGenerator.INSTANCE.createJsonDouble(name, null);
				break;
			case JSON_DATE:
				node = StructureGenerator.INSTANCE.createJsonDate(name, null);
				break;
			case JSON_OBJECT:
				node = StructureGenerator.INSTANCE.createJsonObject(name, null);
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


	@FXML
	public void saveStructure() {
		System.out.println("Structure saved");
	}
	
	@FXML
	public void addElement() {
		System.out.println("Add element");
	}

}
