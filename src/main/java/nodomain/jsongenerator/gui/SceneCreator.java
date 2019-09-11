package nodomain.jsongenerator.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import nodomain.jsongenerator.gui.controller.MenuController;

public class SceneCreator {
	
	private static BorderPane root = new BorderPane();
	
	public Scene createMainScene() {	
		attachTop();
		showMiddle(new MenuController().showMainPage());
        
		Scene scene = new Scene(root,600,600);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}

	private void attachTop() {
		try {
			Parent top = FXMLLoader.load(getClass().getResource("/fxml/top.fxml"));
			root.setTop(top);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public static void showMiddle(Pane pane) {
		root.setCenter(pane);
	}

}
