package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public enum DateElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);		
		
		Label lowerL = new Label("lower bound");
		TextField lowerF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("lower_bound").toString());
		Label upperL = new Label("upper bound");
		TextField upperF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("upper_bound").toString());
		Label patternL = new Label("output pattern");
		TextField patternF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("output_pattern").toString());
		
		GridPane gp = createGrid(name);
		gp.add(lowerL, 0, 1);
		gp.add(lowerF, 1, 1);
		gp.add(upperL, 0, 2);
		gp.add(upperF, 1, 2);
		gp.add(patternL, 0, 3);
		gp.add(patternF, 1, 3);

		pane.setCenter(gp);
		return pane;
	}

}
