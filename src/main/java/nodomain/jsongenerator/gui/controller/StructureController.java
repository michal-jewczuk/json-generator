package nodomain.jsongenerator.gui.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
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
import nodomain.jsongenerator.gui.StructureGenerator;
import nodomain.jsongenerator.main.JsonGenerator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
    public void initialize() {
    	structureFields.getPanes()
    		.addAll(createStructurePanes(JsonGenerator.parseStructureFile()));
    }
	
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


	@FXML
	private void saveStructure() {
		System.out.println("Structure saved");
	}
	
	@FXML
	private void addElement() {	
		List<TitledPane> panes = structureFields.getPanes();
		panes.add(createSinglePane(createMockObject()));
		
		System.out.println("Add element");
	}
	
	public static void removePanel(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		panes.remove(pane);
	}
	
	public static void movePanelUp(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		int index = panes.indexOf(pane);
		if (index > 0) {
			panes.remove(pane);
			panes.add(index - 1, pane);
		}
	}
	
	public static void movePanelDown(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		int index = panes.indexOf(pane);
		if (index < panes.size() - 1) {
			panes.remove(pane);
			panes.add(index + 1, pane);
		}
	}
	
	public static JSONObject createMockObject() {
		int length = 8;
		boolean firstCapital = true;
		boolean allCapital = false;
		String json_string = "{\"type\": \"JSON_STRING\", \"name\": \"string_l8_first_cap\", \"options\" : "
				+ "{\"length\": " + length + ", \"first_cap\": " + firstCapital + ", \"all_cap\": " + allCapital + "}}";
		JSONObject json_object = new JSONObject(json_string);
		
		return json_object;
	}

}
