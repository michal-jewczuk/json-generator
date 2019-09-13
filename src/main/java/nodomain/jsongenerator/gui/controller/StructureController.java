package nodomain.jsongenerator.gui.controller;

import java.util.List;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import nodomain.jsongenerator.gui.generators.PanelGenerator;
import nodomain.jsongenerator.gui.processors.MainProcessor;
import nodomain.jsongenerator.main.JsonGenerator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
    public void initialize() {
    	structureFields.getPanes()
    		.addAll(PanelGenerator.INSTANCE.createStructurePanes(JsonGenerator.parseStructureFile()));
    }

	@FXML
	private void saveStructure(ActionEvent e) {
		//TODO change process to save when all done
		BorderPane bp = (BorderPane) ((Button) e.getSource()).getParent().getParent().getParent();
		MainProcessor.INSTANCE.proccessStructure((Accordion) bp.getCenter());
	}
	
	@FXML
	private void addElement() {	
		List<TitledPane> panes = structureFields.getPanes();
		panes.add(PanelGenerator.INSTANCE.createSinglePane(createMockObject()));
		
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
