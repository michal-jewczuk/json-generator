package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public enum StringElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		String minLength = options == null ? "" : options.get("min_length").toString();
		String maxLength = options == null ? "" : options.get("max_length").toString();
		boolean firstCap = options == null ? false : options.getBoolean("first_cap");
		boolean allCap = options == null ? false : options.getBoolean("all_cap");
		
		Label minLengthL = new Label("min length");
		TextField minLengthF = ComponentGenerator.INSTANCE.generateTextField(minLength);
		Label maxLengthL = new Label("max length");
		TextField maxLengthF = ComponentGenerator.INSTANCE.generateTextField(maxLength);
		CheckBox firstCapCB = ComponentGenerator.INSTANCE.generateCheckBox("first capital");
		firstCapCB.setSelected(firstCap);
		CheckBox allCapCB = ComponentGenerator.INSTANCE.generateCheckBox("all capital");
		allCapCB.setSelected(allCap);
		
		GridPane gp = createGrid(name);
		gp.add(minLengthL, 0, 1);
		gp.add(minLengthF, 1, 1);
		gp.add(maxLengthL, 0, 2);
		gp.add(maxLengthF, 1, 2);
		gp.add(firstCapCB, 1, 3);
		gp.add(allCapCB, 1, 4);
		
		pane.setCenter(gp);
		return pane;
	}

}
