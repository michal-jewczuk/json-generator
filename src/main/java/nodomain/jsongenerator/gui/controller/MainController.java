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
	private static final String INVALID_COUNT = "Invalid format. Setting count to 1";
	private static final String EMPTY_MESSAGE = "";
	
	@FXML
	private TextField countField;
	
	@FXML
	private TextField outputNameField;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Label outputLabel;
	
	@FXML
	private VBox validationBox;
	
	@FXML
	private VBox validationMessages;
	
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
    	validationBox.setVisible(false);
    	Map<String, String> errors = MainValidator.INSTANCE.validateStructure(CURRENT_STRUCTURE);
    	if (errors.size() == 0) {
        	setGenerationParameters();	
        	String fileName = JsonGenerator.generateOutputFile(CURRENT_STRUCTURE, count, outputName);
        	setOutputMessage(fileName);
    	} else {
    		validationBox.setVisible(true);
    		validationMessages.getChildren()
    			.setAll(ComponentGenerator.INSTANCE.returnErrorTexts(errors));
    	}
    }
    
    private void setOutputMessage(String fileName) {
    	String message = "Data written to file: " + fileName;
    	System.out.println(message);
    	outputLabel.setText(message);
    }
    
	private void setGenerationParameters() {
		errorLabel.setText(EMPTY_MESSAGE);
		try {
			count = Integer.parseInt(countField.getText());
			if (count < 1) {
				count = 1;
			}
		} catch (Exception e) {
			count = 1;
			countField.setText(count.toString());
			errorLabel.setText(INVALID_COUNT);
		}
		outputName = outputNameField.getText();
	}
	
}
