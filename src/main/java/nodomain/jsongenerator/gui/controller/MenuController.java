package nodomain.jsongenerator.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import nodomain.jsongenerator.gui.SceneCreator;

public class MenuController {
	
	@FXML
	private void showMain(ActionEvent event) {
		SceneCreator.showMiddle(showMainPage());
	}
	
	@FXML
	private void showStructure(ActionEvent event) {
		SceneCreator.showMiddle(showStructure());
	}
	
	public Pane showMainPage() {
		BorderPane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return pane;
	}
	
	private Pane showStructure() {
		BorderPane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getResource("/fxml/structure.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return pane;
	}

}
