package nodomain.jsongenerator.gui.controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.data.DataType;
import nodomain.jsongenerator.gui.UIMessages;

public class AddController {
	
	private Stage stage;
	private StringBuilder json = new StringBuilder();
	
	@FXML
	private ComboBox<String> elements;
	
	@FXML
	private BorderPane formPane;
	
	public AddController() {
		stage = new Stage();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
			loader.setController(this);
			stage.setScene(new Scene(loader.load(), AppConfig.POPUP_WIDTH, AppConfig.POPUP_HEIGHT));
			stage.setTitle(UIMessages.POPUP_TITLE);
			stage.initModality(Modality.APPLICATION_MODAL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialize() {
		for (DataType type: DataType.values()) {
			elements.getItems().add(type.toString());
		}
	}
	
    public void showStage() {
        stage.showAndWait();
    }
    
	public String getElement() {
		return json.toString();
	}
    
    private void closeStage() {
    	stage.close();
    }
	
	@FXML
	private void displayForm(ActionEvent event) {	 
		String element = elements.getSelectionModel().getSelectedItem().toString();
		DataType type = DataType.valueOf(element);
		Node node = type.createGUIElement("", null, false);
		formPane.setCenter(node);
	}
	
	@FXML
	private void addElement(ActionEvent event) {
		BorderPane parent = (BorderPane) ((Button) event.getSource()).getParent().getParent();
		TilePane top = (TilePane) parent.getTop();
		
		// cast to ComboBox is safe
		@SuppressWarnings("unchecked")
		ComboBox<String> choice = (ComboBox<String>) top.getChildren().get(1);		
		
		if (choice.getValue() == null) {
			closeStage();
			return;
		}
		
		GridPane pane = (GridPane) ((BorderPane) parent.getCenter()).getCenter();
		
		DataType type = DataType.valueOf(choice.getValue());
		json = type.convertToJSON(pane);

		closeStage();
	}

}
