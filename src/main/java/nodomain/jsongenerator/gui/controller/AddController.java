package nodomain.jsongenerator.gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import nodomain.jsongenerator.data.DataType;

public class AddController {
	
	@FXML
	private ComboBox<String> elements;
	
	@FXML
	private BorderPane formPane;
	
	public void initialize() {
		for (DataType type: DataType.values()) {
			if (type.equals(DataType.JSON_OBJECT)) {
				continue;
			}
			elements.getItems().add(type.toString());
		}
	}
	
	@FXML
	private void displayForm(ActionEvent event) {	 
		String element = elements.getSelectionModel().getSelectedItem().toString();
		DataType type = DataType.valueOf(element);
		Node node = type.createGUIElement("", null, false);
		formPane.setCenter(node);
	}

}
