package nodomain.jsongenerator.gui.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
		//panes.add(PanelGenerator.INSTANCE.createSinglePane(createMockObject()));
		
		try {
			clickShow(e);
			updateStructure();
		} catch (IOException e1) {
			System.out.println("Could not load add.fxml");
			e1.printStackTrace();
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
	
	private void clickShow(ActionEvent event) throws IOException {
	    Stage stage = new Stage();

		Parent root = FXMLLoader.load(getClass().getResource("/fxml/add.fxml"));
		stage.setScene(new Scene(root, 400, 400));
		stage.setTitle("Add new element");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
		stage.show();
	}

}
