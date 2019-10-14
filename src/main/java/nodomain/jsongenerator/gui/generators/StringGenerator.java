package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.UIMessages;
import nodomain.jsongenerator.gui.generators.common.ComponentGenerator;
import nodomain.jsongenerator.gui.generators.common.ItemGenerator;

public enum StringGenerator implements ItemGenerator {
	
	INSTANCE;

	@Override
	public Node generateItem(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		String minLength = options == null ? "" : options.get("min_length").toString();
		String maxLength = options == null ? "" : options.get("max_length").toString();
		boolean firstCap = options == null ? false : options.getBoolean("first_cap");
		boolean allCap = options == null ? false : options.getBoolean("all_cap");
		
		Label minLengthL = 
				ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_MIN_LENGTH);
		TextField minLengthF = 
				ComponentGenerator.INSTANCE.generateTextField(minLength);
		Label maxLengthL = 
				ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_MAX_LENGTH);
		TextField maxLengthF = 
				ComponentGenerator.INSTANCE.generateTextField(maxLength);	
		RadioButton rbNone = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_NONE_CAP);
		RadioButton rbFirst = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_FIRST_CAP);
		RadioButton rbAll = 
				ComponentGenerator.INSTANCE.generateRadioButton(UIMessages.LABEL_ALL_CAP);
		
		final ToggleGroup rbtg = new ToggleGroup();
		rbNone.setToggleGroup(rbtg);
		rbFirst.setToggleGroup(rbtg);
		rbAll.setToggleGroup(rbtg);
		
		if (firstCap) {
			rbFirst.setSelected(true);
		} else if (allCap) {
			rbAll.setSelected(true);
		} else {
			rbNone.setSelected(true);
		}
		
		GridPane gp = createGrid(name);
		gp.add(minLengthL, 0, 1);
		gp.add(minLengthF, 1, 1);
		gp.add(maxLengthL, 0, 2);
		gp.add(maxLengthF, 1, 2);
		gp.add(rbNone, 1, 3);
		gp.add(rbFirst, 1, 4);
		gp.add(rbAll, 1, 5);
		
		pane.setCenter(gp);
		return pane;
	}

}
