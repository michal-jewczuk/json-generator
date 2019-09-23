package nodomain.jsongenerator.gui.generators;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import nodomain.jsongenerator.gui.controller.StructureController;

public enum ComponentGenerator {

	INSTANCE;
	
	public TextField generateTextField(String value) {
		TextField tf = new TextField(value);
		tf.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if(!newVal) {
				StructureController.updateStructure();
			}
		});
		
		return tf;
	}
	
	public CheckBox generateCheckBox(String value) {
		CheckBox cb = new CheckBox(value);
		cb.setOnAction((ActionEvent e) -> {
			StructureController.updateStructure();
		});
		
		return cb;
	}
}
