package nodomain.jsongenerator.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.io.ReadWriteUtil;
import nodomain.jsongenerator.main.JsonGenerator;

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
	
    public void initialize() {
    	countField.setText(count.toString());
    	outputNameField.setText(outputName);
    	if (CURRENT_STRUCTURE == null) {
    		CURRENT_STRUCTURE = ReadWriteUtil.readStructure();
    	}
    }

    @FXML
    private void generateJSON(ActionEvent event) {
    	System.out.println(CURRENT_STRUCTURE);
    	setGenerationParameters();	
    	String fileName = JsonGenerator.generateOutputFile(CURRENT_STRUCTURE, count, outputName);
    	setOutputMessage(fileName);
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
