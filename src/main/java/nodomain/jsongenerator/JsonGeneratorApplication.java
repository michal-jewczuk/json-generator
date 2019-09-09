package nodomain.jsongenerator;

import javafx.application.Application;
import javafx.stage.Stage;
import nodomain.jsongenerator.config.AppConfig;
import nodomain.jsongenerator.gui.SceneCreator;

public class JsonGeneratorApplication extends Application {

	public static void main(String[] args) {	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {		
			primaryStage.setTitle(AppConfig.APP_NAME);	 
			primaryStage.setScene(SceneCreator.createMainScene());
			primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
