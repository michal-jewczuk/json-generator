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
		boolean onlyTrue = options == null ? false : options.getBoolean("only_true");
		boolean onlyFalse = options == null ? false : options.getBoolean("only_false");
		
		CheckBox onlyTrueCB = ComponentGenerator.INSTANCE.generateCheckBox("only TRUE");
		onlyTrueCB.setSelected(onlyTrue);
		CheckBox onlyFalseCB = ComponentGenerator.INSTANCE.generateCheckBox("only FALSE");
		onlyFalseCB.setSelected(onlyFalse);	
		
		GridPane gp = createGrid(name);
		gp.add(onlyTrueCB, 1, 1);
		gp.add(onlyFalseCB, 1, 2);
		
		pane.setCenter(gp);
		return pane;
	}

}
