package nodomain.jsongenerator.gui.controller;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.gui.generators.ComponentGenerator;
import nodomain.jsongenerator.io.ReadWriteUtil;
import nodomain.jsongenerator.main.JsonGenerator;
import nodomain.jsongenerator.validators.MainValidator;

public class MainController {
	
	public static String CURRENT_STRUCTURE;
	
	private static Integer count = 1;
	private static String outputName = AppConfig.DEFAULT_OUTPUT_NAME;
	private static final String COUNT_NOT_A_NUMBER = "Could not parse # of objects to create.";
	private static final String COUNT_TOO_SMALL = "# of objects to create has to be greater than 0";
	
	@FXML
	private TextField countField;
	
	@FXML
	private TextField outputNameField;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private VBox validationBox;
	
    public void initialize() {
    	countField.setText(count.toString());
    	outputNameField.setText(outputName);
    	if (CURRENT_STRUCTURE == null) {
    		CURRENT_STRUCTURE = ReadWriteUtil.readStructure();
    	}
    	validationBox.setVisible(false);
    }

    @FXML
    private void generateJSON(ActionEvent event) {
    	Map<String, String> errors = MainValidator.INSTANCE.validateStructure(CURRENT_STRUCTURE);
    	if (errors.size() == 0) {
    		try {
    			setGenerationParameters();
            	String fileName = JsonGenerator.generateOutputFile(CURRENT_STRUCTURE, count, outputName);
            	displayMessage("Data written to file: " + fileName);
    		} catch (IllegalArgumentException e) {
    			displayMessage(e.getMessage());
    		}		
    	} else {
    		validationBox.setVisible(true);
    		validationBox.getChildren()
    			.setAll(ComponentGenerator.INSTANCE.displayValidationErrors(errors));
    	}
    }
    
    private void displayMessage(String message) {
    	validationBox.setVisible(true);
		validationBox.getChildren()
			.setAll(ComponentGenerator.INSTANCE.displayMessage(message));
    }
    
	private void setGenerationParameters() {
		try {
			count = Integer.parseInt(countField.getText());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(COUNT_NOT_A_NUMBER);
		}
		
		if (count < 1) {
			throw new IllegalArgumentException(COUNT_TOO_SMALL);
		}
		outputName = outputNameField.getText();
	}
	
}
