package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public enum BoolElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);		

		CheckBox onlyTrueCB = ComponentGenerator.INSTANCE.generateCheckBox("only TRUE");
		onlyTrueCB.setSelected(options.getBoolean("only_true"));
		CheckBox onlyFalseCB = ComponentGenerator.INSTANCE.generateCheckBox("only FALSE");
		onlyFalseCB.setSelected(options.getBoolean("only_false"));	
		
		GridPane gp = createGrid(name);
		gp.add(onlyTrueCB, 1, 1);
		gp.add(onlyFalseCB, 1, 2);
		
		pane.setCenter(gp);
		return pane;
	}

}
