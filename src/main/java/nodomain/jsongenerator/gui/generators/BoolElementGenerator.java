package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.data.BoolDataOptions;
import nodomain.jsongenerator.data.parsers.BoolDataOptionsParser;

public enum BoolElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject rawOptions) {
		BoolDataOptions options = BoolDataOptionsParser.INSTANCE.parseDataOptions(rawOptions);
		BorderPane pane = createObjectLayout();		

		CheckBox onlyTrueCB = new CheckBox("only TRUE");
		onlyTrueCB.setSelected(options.isOnlyTrue());
		CheckBox onlyFalseCB = new CheckBox("only FALSE");
		onlyFalseCB.setSelected(options.isOnlyFalse());	
		
		GridPane gp = createGrid(name);
		gp.add(onlyTrueCB, 1, 1);
		gp.add(onlyFalseCB, 1, 2);
		
		pane.setCenter(gp);
		return pane;
	}

}