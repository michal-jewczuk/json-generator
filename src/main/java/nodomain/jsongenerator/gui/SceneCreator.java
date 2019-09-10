package nodomain.jsongenerator.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.config.AppConfig;

public class SceneCreator {
	
	private static Integer count = 1;
	private static String outputName = AppConfig.DEFAULT_OUTPUT_NAME;
	
	private static final Label lblOutputMessage = new Label();
	private static final Label lblErrorMessage = new Label();
	
	private static final TextField tfCount = new TextField(count.toString());
	private static final TextField tfFileName = new TextField(outputName);
	

	public Scene createMainScene() {
		
		BorderPane root = new BorderPane();
		attachTop(root);
		attachMiddle(root);
		attachBottom(root);
        
		Scene scene = new Scene(root,400,400);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	private void attachTop(BorderPane root) {
		try {
			Parent top = FXMLLoader.load(getClass().getResource("/fxml/top.fxml"));
			root.setTop(top);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	private static void attachMiddle(BorderPane root) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
        
        Label lblCount = new Label("# of objects to create:");        
        Label lblFileName = new Label("Output file name:");
        
        grid.add(lblCount, 0, 0);
        grid.add(tfCount, 1, 0);
        grid.add(lblFileName, 0, 1);
        grid.add(tfFileName, 1, 1);
        grid.add(lblOutputMessage, 0, 2, 2, 1);
        grid.add(lblErrorMessage, 0, 3, 2, 1);
        
        root.setCenter(grid);	
	}
	
	private void attachBottom(BorderPane root) {   
		try {
			Parent bottom = FXMLLoader.load(getClass().getResource("/fxml/bottom.fxml"));
			root.setBottom(bottom);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void setGenerationParameters() {
		try {
			count = Integer.parseInt(tfCount.getText());
			if (count < 1) {
				count = 1;
			}
		} catch (Exception e) {
			count = 1;
			setCountValue(count);
			setErrorMessage("Invalid format. Setting count to 1");
		}
		outputName = tfFileName.getText();
	}

	public static void setOutputMessage(String message) {
		lblOutputMessage.setText(message);
	}
	
	public static void setErrorMessage(String message) {
		lblErrorMessage.setText(message);
	}
	
	public static void setCountValue(Integer count) {
		tfCount.setText(count.toString());
	}
	
	public static Integer getCount() {
		return count;
	}
	
	public static String getOutputName() {
		return outputName;
	}
}
