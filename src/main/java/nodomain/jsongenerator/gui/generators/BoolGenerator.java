package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.UIMessages;
import nodomain.jsongenerator.gui.generators.common.ComponentGenerator;
import nodomain.jsongenerator.gui.generators.common.ItemGenerator;

public enum BoolGenerator implements ItemGenerator {
	
	INSTANCE;

	@Override
	public Node generateItem(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);		
		boolean onlyTrue = options == null ? false : options.getBoolean("only_true");
		boolean onlyFalse = options == null ? false : options.getBoolean("only_false");
		
		RadioButton rbNone = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_TRUE_OR_FALSE);
		RadioButton rbTrue = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_ONLY_TRUE);
		RadioButton rbFalse = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_ONLY_FALSE);
		
		final ToggleGroup rbtg = new ToggleGroup();
		rbNone.setToggleGroup(rbtg);
		rbTrue.setToggleGroup(rbtg);
		rbFalse.setToggleGroup(rbtg);
		
		if (onlyTrue) {
			rbTrue.setSelected(true);
		} else if (onlyFalse) {
			rbFalse.setSelected(true);
		} else {
			rbNone.setSelected(true);
		}	
		
		GridPane gp = createGrid(name);
		gp.add(rbNone, 1, 1);
		gp.add(rbTrue, 1, 2);
		gp.add(rbFalse, 1, 3);
		
		pane.setCenter(gp);
		return pane;
	}

}
