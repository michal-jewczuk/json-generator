package nodomain.jsongenerator.gui.controller;

import java.util.List;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.gui.generators.PanelGenerator;
import nodomain.jsongenerator.gui.processors.MainProcessor;
import nodomain.jsongenerator.io.ReadWriteUtil;
import nodomain.jsongenerator.main.JsonGenerator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
	private static Accordion staticStructure;
	
    public void initialize() {
    	structureFields.getPanes()
    		.addAll(PanelGenerator.INSTANCE
    					.createStructurePanes(JsonGenerator.parseStructureFile(MainController.CURRENT_STRUCTURE)));
    	staticStructure = structureFields;
    }
    
	@FXML
	private void saveStructure(ActionEvent e) {
		BorderPane bp = (BorderPane) ((Button) e.getSource()).getParent().getParent().getParent();
		StringBuilder structure = MainProcessor.INSTANCE.proccessStructure((Accordion) bp.getCenter());
		ReadWriteUtil.writeToFile(structure, AppConfig.CONFIGURATION_FILE);
	}
	
	@FXML
	private void addElement(ActionEvent e) {	
		List<TitledPane> panes = structureFields.getPanes();
		panes.add(PanelGenerator.INSTANCE.createSinglePane(clickShow()));
		updateStructure();		
	}
	
	public static void removePanel(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		panes.remove(pane);
		updateStructure();
	}
	
	public static void movePanelUp(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		int index = panes.indexOf(pane);
		if (index > 0) {
			panes.remove(pane);
			panes.add(index - 1, pane);
		}
		updateStructure();
	}
	
	public static void movePanelDown(TitledPane pane) {
		List<TitledPane> panes = ((Accordion) pane.getParent()).getPanes();
		int index = panes.indexOf(pane);
		if (index < panes.size() - 1) {
			panes.remove(pane);
			panes.add(index + 1, pane);
		}
		updateStructure();
	}
	
	public static JSONObject createMockObject() {
		int lowerBound = 25;
		int upperBound = 67;
		String json_string = "{\"type\": \"JSON_NUMBER\", \"name\": \"number_25_to_67\", \"options\" : "
				+ "{\"lower_bound\": " + lowerBound + ", \"upper_bound\": " + upperBound + "}}";
		JSONObject json_object = new JSONObject(json_string);
		
		return json_object;
	}
	
	private static void updateStructure() {
		MainController.CURRENT_STRUCTURE = 
				MainProcessor.INSTANCE.proccessStructure(staticStructure).toString(); 
	}
	
	private JSONObject clickShow() {
		AddController addController = new AddController();
		addController.showStage();
		return new JSONObject(addController.getElement());
	}

}
