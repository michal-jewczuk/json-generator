package nodomain.jsongenerator.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.main.JsonGenerator;

public class SceneCreator {
	
	private static Integer count = 1;
	private static String outputName = AppConfig.DEFAULT_OUTPUT_NAME;
	
	private static final Insets insets = new Insets(15, 12, 15, 12);
	private static final Label lblOutputMessage = new Label();
	private static final Label lblErrorMessage = new Label();
	
	private static final TextField tfCount = new TextField(count.toString());
	private static final TextField tfFileName = new TextField(outputName);
	

	public static Scene createMainScene() {
		
		BorderPane root = new BorderPane();
		attachTop(root);
		attachMiddle(root);
		attachBottom(root);
        
		Scene scene = new Scene(root,400,400);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	private static void attachTop(BorderPane root) {
	    Button buttonMain = new Button(UIMessages.MENU_MAIN);
	    Button buttonStructure = new Button(UIMessages.MENU_STRUCTURE);
	    buttonMain.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    buttonStructure.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    TilePane tileButtons = new TilePane(Orientation.HORIZONTAL);
	    tileButtons.setPadding(insets);
	    tileButtons.setHgap(10);
	    tileButtons.setVgap(12);
	    tileButtons.getChildren().addAll(buttonMain, buttonStructure);
	    tileButtons.setStyle("-fx-background-color: #336699;");
	    
	    root.setTop(tileButtons);
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
	
	private static void attachBottom(BorderPane root) {
		BorderPane bottom = new BorderPane();
		bottom.setPadding(insets);
		bottom.setStyle("-fx-background-color: #336699;");
        Button btn = new Button();
        btn.setText(UIMessages.BUTTON_GENERATE);
        btn.setOnAction(new EventHandler<ActionEvent>() {	 
            @Override
            public void handle(ActionEvent event) {
            	setErrorMessage("");
            	setGenerationParameters();
            	
            	String fileName = JsonGenerator.generateOutputFile(count, outputName);
        		String message = "Data written to file: " + fileName;
        		
        		System.out.println(message);
        		setOutputMessage(message);
            }
        });
        bottom.setCenter(btn);
        
        root.setBottom(bottom);
	}
	
	protected static void setGenerationParameters() {
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

	private static void setOutputMessage(String message) {
		lblOutputMessage.setText(message);
	}
	
	private static void setErrorMessage(String message) {
		lblErrorMessage.setText(message);
	}
	
	private static void setCountValue(Integer count) {
		tfCount.setText(count.toString());
	}
}
