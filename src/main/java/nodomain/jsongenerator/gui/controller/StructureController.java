package nodomain.jsongenerator.gui.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.gui.generators.ComponentGenerator;
import nodomain.jsongenerator.gui.generators.PanelGenerator;
import nodomain.jsongenerator.gui.processors.MainProcessor;
import nodomain.jsongenerator.io.ReadWriteUtil;
import nodomain.jsongenerator.main.JsonGenerator;
import nodomain.jsongenerator.validators.MainValidator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
	@FXML
	private VBox validationBox;
	
	@FXML
	private VBox validationMessages;
	
	private static Accordion staticStructure;
	
    public void initialize() {
    	structureFields.getPanes()
    		.addAll(PanelGenerator.INSTANCE
    					.createStructurePanes(JsonGenerator.parseStructureFile(MainController.CURRENT_STRUCTURE)));
    	staticStructure = structureFields;
    	validationBox.setVisible(false);
    }
    
	@FXML
	private void saveStructure(ActionEvent e) {
		validationBox.setVisible(false);
		BorderPane bp = (BorderPane) ((Button) e.getSource()).getParent().getParent().getParent();
		StringBuilder structure = MainProcessor.INSTANCE.proccessStructure((Accordion) bp.getCenter());
		Map<String, String> errors = MainValidator.INSTANCE.validateStructure(structure.toString());
    	if (errors.size() == 0) {
    		ReadWriteUtil.writeToFile(structure, AppConfig.CONFIGURATION_FILE);
    	} else {
    		validationBox.setVisible(true);
    		validationMessages.getChildren()
    			.setAll(ComponentGenerator.INSTANCE.returnErrorLabels(errors));
    	}
	}
	
	@FXML
	private void addElement(ActionEvent e) {	
		List<TitledPane> panes = structureFields.getPanes();
		JSONObject element = showFormAddElement();
		if (element != null) {
			panes.add(PanelGenerator.INSTANCE.createSinglePane(element));
			updateStructure();				
		}
	}
	
	@FXML
	private void validateStructure(ActionEvent e) {
		validationBox.setVisible(false);
		BorderPane bp = (BorderPane) ((Button) e.getSource()).getParent().getParent().getParent();
		StringBuilder structure = MainProcessor.INSTANCE.proccessStructure((Accordion) bp.getCenter());
		Map<String, String> errors = MainValidator.INSTANCE.validateStructure(structure.toString());
    	if (errors.size() == 0) {
    		System.out.println("No errors");
    	} else {
    		validationBox.setVisible(true);
    		validationMessages.getChildren()
    			.setAll(ComponentGenerator.INSTANCE.returnErrorLabels(errors));
    	}
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
	
	public static void updateStructure() {
		MainController.CURRENT_STRUCTURE = 
				MainProcessor.INSTANCE.proccessStructure(staticStructure).toString(); 
		System.out.println(MainController.CURRENT_STRUCTURE);
	}
	
	private JSONObject showFormAddElement() {
		AddController addController = new AddController();
		addController.showStage();
		String element = addController.getElement();
		if (element.length() == 0) {
			return null;
		} else {
			return new JSONObject(addController.getElement());
		}
	}

}
