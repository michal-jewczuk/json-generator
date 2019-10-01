package nodomain.jsongenerator.gui.generators.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.gui.UIMessages;
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
		tf.setMaxWidth(AppConfig.TF_MAX_WIDTH);
		
		return tf;
	}
	
	public CheckBox generateCheckBox(String value) {
		CheckBox cb = new CheckBox(value);
		cb.setOnAction((ActionEvent e) -> {
			StructureController.updateStructure();
		});
		
		return cb;
	}
	
	public Label generateLabel(String value) {
		Label label = new Label(value);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		return label;
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
	
	public List<Node> displayValidationErrors(Map<String, String> errors) {
		List<Node> nodes = new ArrayList<>();
		nodes.add(new Label(UIMessages.MESSAGE_VALIDATION_ERRORS));
		nodes.add(new Separator());
		
		VBox messages = new VBox(12);
		messages.getChildren().addAll(returnErrorTexts(errors));
		nodes.add(messages);
		nodes.add(new Separator());
		
		return nodes;
	}
	
	public List<Node> displayMessage(String message) {
		List<Node> nodes = new ArrayList<>();
		nodes.add(new Label(message));
		nodes.add(new Separator());
		
		return nodes;
	}
	
}
