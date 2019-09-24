package nodomain.jsongenerator.gui.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
	
	public List<TextFlow> returnErrorTexts(Map<String, String> errors) {	
		List<TextFlow> texts = new ArrayList<>();
		
		errors.forEach((key, value) -> {		
			Text key_text = new Text(key + ": ");
				key_text.setFill(Color.WHITE);
				key_text.setStyle("-fx-font-weight: bold");
			Text value_text = new Text(value);
				value_text.setFill(Color.WHITE);

			texts.add(new TextFlow(key_text, value_text));
		});
		
		return texts;
	}
}
