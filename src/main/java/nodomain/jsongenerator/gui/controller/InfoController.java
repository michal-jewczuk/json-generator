package nodomain.jsongenerator.gui.controller;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class InfoController {
	
	@FXML
	private WebView browser;
	
	public void initialize() {
		WebEngine webEngine = browser.getEngine();
		URL url = this.getClass().getResource("/html/info.html");
		webEngine.load(url.toString());
		System.out.println(webEngine.getTitle());
	}

}
