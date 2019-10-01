package nodomain.jsongenerator.gui.generators;

import org.json.JSONObject;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import nodomain.jsongenerator.gui.UIMessages;
import nodomain.jsongenerator.gui.generators.common.ComponentGenerator;
import nodomain.jsongenerator.gui.generators.common.ElementGenerator;

public enum PatternElementGenerator implements ElementGenerator {
	
	INSTANCE;

	@Override
	public Node generateElement(String name, JSONObject options, boolean showButtons) {
		BorderPane pane = createObjectLayout(showButtons);
		String pattern = options == null ? "" : options.get("pattern").toString();
		String connector = options == null ? "" : options.get("connector").toString();
		boolean allCap = options == null ? false : options.getBoolean("all_cap");
		
		Label patternL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_PATTERN);
		TextField patternF = ComponentGenerator.INSTANCE.generateTextField(pattern);
		Label connectorL = ComponentGenerator.INSTANCE.generateLabel(UIMessages.LABEL_CONNECTOR);
		TextField connectorF = ComponentGenerator.INSTANCE.generateTextField(connector);
		CheckBox allCapCB = ComponentGenerator.INSTANCE.generateCheckBox(UIMessages.LABEL_ALL_CAP);
		allCapCB.setSelected(allCap);
		
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
