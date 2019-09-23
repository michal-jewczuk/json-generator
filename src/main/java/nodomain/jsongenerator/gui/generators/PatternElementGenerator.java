package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.PatternDataOptions;
import nodomain.jsongenerator.data.parsers.PatternDataOptionsParser;

public enum PatternElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject rawOptions, boolean showButtons) {
		PatternDataOptions options = PatternDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
		BorderPane pane = createObjectLayout(showButtons);
		
		Label patternL = new Label("pattern");
		TextField patternF = ComponentGenerator.INSTANCE.generateTextField(options.getPattern());
		Label connectorL = new Label("connector");
		TextField connectorF = ComponentGenerator.INSTANCE.generateTextField(options.getConnector());
		CheckBox allCapCB = ComponentGenerator.INSTANCE.generateCheckBox("all capital");
		allCapCB.setSelected(options.isAllCapital());
		
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
