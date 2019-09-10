package nodomain.jsongenerator.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import nodomain.jsongenerator.gui.SceneCreator;
import nodomain.jsongenerator.main.JsonGenerator;

public class MainController {

    @FXML
    private void generateJSON(ActionEvent event) {
    	SceneCreator.setErrorMessage("");
    	SceneCreator.setGenerationParameters();
    	
    	String fileName = JsonGenerator.generateOutputFile(SceneCreator.getCount(), SceneCreator.getOutputName());
		String message = "Data written to file: " + fileName;
		
		System.out.println(message);
		SceneCreator.setOutputMessage(message);
    }
	
	
}
