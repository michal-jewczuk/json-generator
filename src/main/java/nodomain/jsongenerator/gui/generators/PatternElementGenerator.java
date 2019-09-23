package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public enum PatternElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		
		Label patternL = new Label("pattern");
		TextField patternF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("pattern").toString());
		Label connectorL = new Label("connector");
		TextField connectorF = ComponentGenerator.INSTANCE
					.generateTextField(options.get("connector").toString());
		CheckBox allCapCB = ComponentGenerator.INSTANCE.generateCheckBox("all capital");
		allCapCB.setSelected(options.getBoolean("all_cap"));
		
		GridPane gp = createGrid(name);
		gp.add(patternL, 0, 1);
		gp.add(patternF, 1, 1);
		gp.add(connectorL, 0, 2);
		gp.add(connectorF, 1, 2);
		gp.add(allCapCB, 1, 3);

		pane.setCenter(gp);
		return pane;
	}

}
