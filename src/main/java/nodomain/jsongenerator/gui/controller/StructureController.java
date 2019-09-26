package nodomain.jsongenerator.gui.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
		StringBuilder structure = processCurrentStructure(e);
		Map<String, String> errors = MainValidator.INSTANCE.validateStructure(structure.toString());
    	if (errors.size() == 0) {
    		ReadWriteUtil.writeToFile(structure, AppConfig.CONFIGURATION_FILE);
    		displaySuccessMessage(
    				ComponentGenerator.INSTANCE.displayMessage("The structure was saved."));
    	} else {
    		displayValidationErrors(errors);
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
		StringBuilder structure = processCurrentStructure(e);
		Map<String, String> errors = MainValidator.INSTANCE.validateStructure(structure.toString());
    	if (errors.size() == 0) {
    		displaySuccessMessage(
    				ComponentGenerator.INSTANCE.displayMessage("The structure is valid."));
    	} else {
    		displayValidationErrors(errors);
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
	
	private void displayValidationErrors(Map<String, String> errors) {
		validationBox.setVisible(true);
		validationBox.getChildren()
			.setAll(ComponentGenerator.INSTANCE.displayValidationErrors(errors));
	}
	
	private void displaySuccessMessage(List<Node> nodes) {
		validationBox.setVisible(true);
		validationBox.getChildren().setAll(nodes);
	}
	
	private StringBuilder processCurrentStructure(ActionEvent event) {
		BorderPane bp = (BorderPane) ((Button) event.getSource()).getParent().getParent().getParent();
		Accordion panels = (Accordion) ((ScrollPane) bp.getCenter()).getContent();
		panels.setExpandedPane(null);
		return MainProcessor.INSTANCE.proccessStructure(panels);
	}

}
