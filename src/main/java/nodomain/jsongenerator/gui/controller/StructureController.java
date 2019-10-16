package nodomain.jsongenerator.gui.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.gui.UIMessages;
import nodomain.jsongenerator.gui.generators.common.ComponentGenerator;
import nodomain.jsongenerator.gui.generators.common.PanelGenerator;
import nodomain.jsongenerator.gui.processors.common.MainProcessor;
import nodomain.jsongenerator.io.ReadWriteUtil;
import nodomain.jsongenerator.main.JsonGenerator;
import nodomain.jsongenerator.validators.common.MainValidator;

public class StructureController {
	
	@FXML
	private Accordion structureFields;
	
	@FXML
	private VBox validationBox;
	
	private static Accordion staticStructure;
	final FileChooser fileChooser = new FileChooser();
	
    public void initialize() {
    	validationBox.setVisible(false);
    	setStructure(MainController.CURRENT_STRUCTURE);
    }
	
	@FXML
	private void saveStructure(ActionEvent e) {
		StringBuilder structure = processCurrentStructure(e);
		Map<String, String> errors = MainValidator.INSTANCE.validateStructure(structure.toString());
    	if (errors.size() == 0) {
    		configureFileChooser(fileChooser, UIMessages.SAVE_STRUCTURE_TITLE);
    		File file = fileChooser.showSaveDialog(structureFields.getScene().getWindow());
    		
    		if (file != null) {
    			ReadWriteUtil.writeToFile(structure, file.getAbsolutePath());
        		displayMessage(UIMessages.MESSAGE_SAVED + file.getName());
    		}    		
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
    		displayMessage(UIMessages.MESSAGE_VALID);
    	} else {
    		displayValidationErrors(errors);
    	}
	}
	
	@FXML
	private void clearStructure() {
		structureFields.getPanes().clear();
		displayMessage(UIMessages.MESSAGE_CLEARED);
		updateStructure();
	}
	
	@FXML
	private void loadStructure(ActionEvent event) {
		configureFileChooser(fileChooser, UIMessages.LOAD_STRUCTURE_TITLE);
		File file = fileChooser.showOpenDialog(structureFields.getScene().getWindow());
		if (file != null) {
			String loadedStructure = ReadWriteUtil.readStructure(file.getAbsolutePath());
			displayMessage(UIMessages.MESSAGE_LOADED);
			setStructure(loadedStructure);
	    	updateStructure();	
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
	
	private static void disableButtons(List<TitledPane> panes) {
		int count = panes.size();
		for (int i = 0; i < count; i++) {
			TitledPane pane = panes.get(i);
			Button up = extractButton(pane, 0);
			Button down = extractButton(pane, 1);
			up.setDisable(i == 0);
			down.setDisable(i == (count - 1));
			DataType type = DataType.valueOf(MainProcessor.INSTANCE.getType(pane.getText()));
			if (type.equals(DataType.JSON_OBJECT)) {
				disableButtons(extractChildrenFromJsonObjectPane(pane));
			}
		}		
	}

	private static Button extractButton(TitledPane pane, int i) {
		HBox hb = (HBox) ((BorderPane) pane.getContent()).getRight();
		return (Button) ( (TilePane) hb.getChildren().get(1)).getChildren().get(i);
	}
	
	private static List<TitledPane> extractChildrenFromJsonObjectPane(TitledPane pane) {
		Accordion acc = (Accordion) ((GridPane) ((BorderPane) pane.getContent())
										.getCenter())
									.getChildren().get(6);
		return acc.getPanes();
	}

	public static void updateStructure() {
		MainController.CURRENT_STRUCTURE = 
				MainProcessor.INSTANCE.proccessStructure(staticStructure).toString(); 
		disableButtons(staticStructure.getPanes());
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
	
	private void displayMessage(String message) {
		List<Node> nodes = ComponentGenerator.INSTANCE.displayMessage(message);
		validationBox.setVisible(true);
		validationBox.getChildren().setAll(nodes);
	}
	
	private StringBuilder processCurrentStructure(ActionEvent event) {
		structureFields.setExpandedPane(null);
		return MainProcessor.INSTANCE.proccessStructure(structureFields);
	}
	
    public static void configureFileChooser(final FileChooser fileChooser, final String title) {      
    	fileChooser.setTitle(title);
    	fileChooser.setInitialDirectory(
    		new File(System.getProperty(AppConfig.STARTING_DIR))
        );                 
    	fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
        	new FileChooser.ExtensionFilter("json", "*.json"),
        	new FileChooser.ExtensionFilter("All files", "*.*")
        );
    }
    
    private void setStructure(String structure) {
    	structureFields.getPanes().clear();
    	try {
    		MainValidator.INSTANCE.validateStructure(structure.toString());
    	} catch (IllegalArgumentException iae) {
    		structure = AppConfig.EMPTY_STRUCTURE;
    		displayMessage(AppConfig.INVALID_ELEMENTS);
    	} catch (JSONException je) {	
    		structure = AppConfig.EMPTY_STRUCTURE;
    		displayMessage(AppConfig.NOT_A_JSON);   		
    	} catch (Exception oe) {
    		structure = AppConfig.EMPTY_STRUCTURE;
    		displayMessage(AppConfig.OTHER_PARSING_ERROR);
    	}
    	
    	structureFields.getPanes()
			.addAll(PanelGenerator.INSTANCE.createStructurePanes(
				JsonGenerator.parseStructureFile(structure)));
    	disableButtons(structureFields.getPanes());
    	staticStructure = structureFields;
    }

}
