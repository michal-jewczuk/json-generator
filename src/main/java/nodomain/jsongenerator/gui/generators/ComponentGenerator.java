package nodomain.jsongenerator.gui.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
	
	public List<Label> returnErrorLabels(Map<String, String> errors) {	
		List<Label> labels = new ArrayList<>();
		errors.forEach((key, value) -> {
			labels.add(new Label(key + ": " + value));
		});
		
		return labels;
	}
}
